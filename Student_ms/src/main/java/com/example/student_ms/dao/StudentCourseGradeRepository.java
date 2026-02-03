package com.example.student_ms.dao;

import com.example.student_ms.model.StudentCourseGrade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface StudentCourseGradeRepository extends JpaRepository<StudentCourseGrade, Long> {
    List<StudentCourseGrade> findByStudentId(Long studentId);

    List<StudentCourseGrade> findByCourseId(Long courseId);
    List<StudentCourseGrade> findByAcademicYear(Integer academicYear);
}
