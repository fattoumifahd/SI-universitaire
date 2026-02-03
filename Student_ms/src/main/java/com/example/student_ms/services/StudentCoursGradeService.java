package com.example.student_ms.services;

import com.example.student_ms.dao.StudentCourseGradeRepository;
import com.example.student_ms.dao.StudentRepository;
import com.example.student_ms.exception.ResourceNotFoundException;
import com.example.student_ms.model.StudentCourseGrade;
import com.example.student_ms.model.dtos.*;
import com.example.student_ms.model.mapper.StudentGradeMapper;
import com.example.student_ms.proxy.CourProxy;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class StudentCoursGradeService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StudentGradeMapper  studentGradeMapper;

    private StudentCourseGradeRepository studentCourseGradeRepository;

    private StudentRepository studentRepository;

    @Autowired
    private CourProxy courProxy;

    public StudentCoursGradeService(StudentCourseGradeRepository studentCourseGradeRepository, StudentRepository studentRepository) {
        this.studentCourseGradeRepository = studentCourseGradeRepository;
        this.studentRepository = studentRepository;

    }

    public StudentCourseGrade getStudentCourseGrade(Long id){
        return studentCourseGradeRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No grad found with id " + id));
    }

    public List<StudentGradeDTO> getAllStudentGrade() {
        List<StudentCourseGrade> grades = studentCourseGradeRepository.findAll();
        return convertEntitiesToDTOs(grades);
    }


    public List<StudentGradeDTO> getStudentGradeByCourseId(Long courseId) {
        List<StudentCourseGrade> grades = studentCourseGradeRepository.findByCourseId(courseId);
        return convertEntitiesToDTOs(grades);
    }



    public List<StudentGradeDTO> convertEntitiesToDTOs(List<StudentCourseGrade> studentCourseGradeList) {
        List<StudentGradeDTO> studentGradeDTOs = new ArrayList<>();
        for (StudentCourseGrade grade : studentCourseGradeList) {
            StudentGradeDTO dto = modelMapper.map(grade, StudentGradeDTO.class);
            studentGradeDTOs.add(dto);
        }
        return studentGradeDTOs;
    }

    public List<StudentGradeDTO> getStudentGradeByStudentId(Long studentId) {
        return convertEntitiesToDTOs(studentCourseGradeRepository.findByStudentId(studentId));
    }

    public StudentCourseGrade createStudentCourseGrade(StudentGradeDTO gradeDTO) {
//        Integer currentSemester = studentRepository.findById(gradeDTO.getStudentId()).get().getCurrentSemester();
        gradeDTO.setSemesterNumber(studentRepository.findById(gradeDTO.getStudentId()).get().getCurrentSemester());
        StudentCourseGrade studentGrade = studentGradeMapper.toEntity(gradeDTO);
//        studentGrade.setSemesterNumber( currentSemester );
        return studentCourseGradeRepository.save(studentGrade);
    }

    public StudentGradeDTO getStudentGradeByStudentIdAndCourseId(Long studentId, Long courseId) {
        List<StudentGradeDTO> gradesByStudentId = getStudentGradeByStudentId(studentId);
        gradesByStudentId.stream().filter(grade -> grade.getCourseId().equals(courseId)).findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("No grade found with id " + courseId));
        return modelMapper.map(gradesByStudentId.get(0), StudentGradeDTO.class);
    }

    public void deleteStudentGrade(Long id) {
        StudentCourseGrade studentGrade = getStudentCourseGrade(id);
        studentCourseGradeRepository.delete(studentGrade);
    }

    public StudentCourseGrade updateStudentCourseGrade(Long id , StudentGradeDTO gradeDTO) {
        StudentCourseGrade studentGrade = getStudentCourseGrade(id);
        studentGrade = studentGradeMapper.toEntity(gradeDTO);
        return studentCourseGradeRepository.save(studentGrade);


    }

    public List<StudentGradeDTO> getGradesForBatch(Integer academicYear) {
         List<StudentCourseGrade> studentGradeList =  studentCourseGradeRepository.findByAcademicYear(academicYear);
         List<StudentGradeDTO> studentGradeDTOs = new ArrayList<>();
         for (StudentCourseGrade grade : studentGradeList) {
             StudentGradeDTO dto = modelMapper.map(grade, StudentGradeDTO.class);
             studentGradeDTOs.add(dto);
         }
         return studentGradeDTOs;
    }

}
