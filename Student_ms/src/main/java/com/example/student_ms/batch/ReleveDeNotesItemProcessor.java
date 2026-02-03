package com.example.student_ms.batch;

import com.example.student_ms.dao.StudentCourseGradeRepository;
import com.example.student_ms.model.Student;
import com.example.student_ms.model.StudentCourseGrade;
import com.example.student_ms.model.dtos.*;
import com.example.student_ms.proxy.CourProxy;
import com.example.student_ms.proxy.ModuleProxy;
import com.example.student_ms.proxy.SemesterProxy;
import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class ReleveDeNotesItemProcessor implements ItemProcessor<Student, ReleveDeNotes> {

    @Autowired
    private StudentCourseGradeRepository gradeRepo;

    @Autowired
    private CourProxy courProxy;

    @Autowired
    private ModuleProxy moduleProxy;

    @Autowired
    private SemesterProxy semesterProxy;

    // Optional: simple caching to avoid repeated calls
    private final Map<Long, CoursBean> courseCache = new ConcurrentHashMap<>();
    private final Map<Long, ModuleDTO> moduleCache = new ConcurrentHashMap<>();
    private final Map<Long, SemesterDTO> semesterCache = new ConcurrentHashMap<>();

    @Override
    public ReleveDeNotes process(Student student) throws Exception {
        List<StudentCourseGrade> grades = gradeRepo.findByStudentId(student.getId());
        if (grades.isEmpty()) {
            return null; // skip students with no grades
        }

        ReleveDeNotes releve = new ReleveDeNotes();
        releve.setStudentId(student.getId());
        releve.setStudentFullName(student.getFirstName() + " " + student.getLastName());
        releve.setFieldOfStudy(student.getField());
        releve.setCurrentSemester(student.getCurrentSemester());

        List<CourseTranscriptEntry> entries = new ArrayList<>();

        for (StudentCourseGrade grade : grades) {
            try {
                // 1. Get Course
                CoursBean cour = courseCache.computeIfAbsent(grade.getCourseId(),
                        id -> courProxy.getCourById(id));

                // 2. Get Module
                ModuleDTO module = moduleCache.computeIfAbsent(cour.getModuleId(),
                        id -> moduleProxy.getModuleById(id));

                // 3. Get Semester
                SemesterDTO semester = semesterCache.computeIfAbsent(module.getSemesterId(),
                        id -> semesterProxy.getSemesterById(id));

                // 4. Build Entry
                CourseTranscriptEntry entry = new CourseTranscriptEntry();
                entry.setCourseName(cour.getName());
                entry.setModuleName(module.getModuleName());
                entry.setSemesterName(semester.getName());
                entry.setSemesterStartDate(semester.getStartDate());
                entry.setSemesterEndDate(semester.getEndDate());

                entry.setContinuousAssessment(grade.getContinuousAssessment());
                entry.setFinalExam(grade.getFinalExam());
                entry.setFinalGrade(grade.getFinalGrade());
                entry.setCourseCoefficient(cour.getCoefficient());
                entry.setModuleCoefficient(module.getModuleCoefficient());

                entry.setAcademicYear(grade.getAcademicYear());
                entry.setSemesterNumber(grade.getSemesterNumber());

                entries.add(entry);

            } catch (Exception e) {
                // Log error but continue (or fail fast?)
                System.err.println("Failed to enrich grade ID=" + grade.getId() + ": " + e.getMessage());
            }
        }

        releve.setEntries(entries);

        // Optional: Calculate weighted average
        calculateWeightedAverage(releve);

        return releve;
    }

    private void calculateWeightedAverage(ReleveDeNotes releve) {
        double totalWeighted = 0.0;
        double totalCoeff = 0.0;

        for (CourseTranscriptEntry e : releve.getEntries()) {
            if (e.getFinalGrade() != null && e.getCourseCoefficient() > 0) {
                totalWeighted += e.getFinalGrade() * e.getCourseCoefficient();
                totalCoeff += e.getCourseCoefficient();
            }
        }

        if (totalCoeff > 0) {
            releve.setOverallWeightedAverage(totalWeighted / totalCoeff);
        }
    }
}