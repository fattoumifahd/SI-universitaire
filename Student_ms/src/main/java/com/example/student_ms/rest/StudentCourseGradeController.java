package com.example.student_ms.rest;

import com.example.student_ms.dao.StudentCourseGradeRepository;
import com.example.student_ms.dao.StudentRepository;
import com.example.student_ms.model.Student;
import com.example.student_ms.model.StudentCourseGrade;
import com.example.student_ms.model.dtos.CoursBean;
import com.example.student_ms.model.dtos.CoursDTO;
import com.example.student_ms.model.dtos.StudentGradeDTO;
import com.example.student_ms.services.StudentCoursGradeService;
import com.example.student_ms.services.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/student/grade")
@RequiredArgsConstructor
public class StudentCourseGradeController {
    @Autowired
    private StudentCourseGradeRepository studentCourseGradeRepository;
    @Autowired
    private StudentCoursGradeService studentCoursGradeService;
    @Autowired
    private StudentService studentService;

    @GetMapping("/{id}")
    public ResponseEntity<StudentCourseGrade> getStudentCourseGrade(@PathVariable Long id) {
        StudentCourseGrade grade = studentCoursGradeService.getStudentCourseGrade(id);
        return ResponseEntity.ok(grade);
    }

    @GetMapping
    public ResponseEntity<List<StudentGradeDTO>> getAllStudentGrades() {
        List<StudentGradeDTO> grades = studentCoursGradeService.getAllStudentGrade();
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity<List<StudentGradeDTO>> getStudentGradesByCourseId(@PathVariable Long courseId) {
        List<StudentGradeDTO> grades = studentCoursGradeService.getStudentGradeByCourseId(courseId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<StudentGradeDTO>> getStudentGradesByStudentId(@PathVariable Long studentId) {
        List<StudentGradeDTO> grades = studentCoursGradeService.getStudentGradeByStudentId(studentId);
        return ResponseEntity.ok(grades);
    }

    @GetMapping("/student/{studentId}/course/{courseId}")
    public ResponseEntity<StudentGradeDTO> getStudentGradeByStudentIdAndCourseId(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {
        StudentGradeDTO grade = studentCoursGradeService.getStudentGradeByStudentIdAndCourseId(studentId, courseId);
        return ResponseEntity.ok(grade);
    }

    @PostMapping
    public ResponseEntity<StudentCourseGrade> createStudentCourseGrade(@RequestBody StudentGradeDTO gradeDTO) {
        StudentCourseGrade createdGrade = studentCoursGradeService.createStudentCourseGrade(gradeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdGrade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<StudentCourseGrade> updateStudentCourseGrade(@PathVariable Long id, @RequestBody StudentGradeDTO gradeDTO) {
        StudentCourseGrade grade =  studentCoursGradeService.updateStudentCourseGrade(id, gradeDTO);
        return ResponseEntity.ok(grade);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteStudentCourseGrade(@PathVariable Long id) {
        studentCourseGradeRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
//    private  StudentCoursGradeService studentCoursGradeService;

//    @Autowired
//    private StudentService  studentService;
//
    @GetMapping("/{id}/cours")
    public ResponseEntity<List<CoursBean>> getStudentCourses(@PathVariable Long id){
        Student student = studentService.findById(id).get();
        Long semesterId = Long.valueOf(student.getCurrentSemester());
        List<CoursBean> coures = studentService.getCoursesBySemester(semesterId);
//        studentService.getCoursesBySemester(semesterId);
        return new ResponseEntity<>(coures, HttpStatus.OK);
    }

    @GetMapping("/grades/batch")
    public List<StudentGradeDTO> getStudentGrades(@RequestParam Integer academicYear) {
        return studentCoursGradeService.getGradesForBatch(academicYear);
    }


}
