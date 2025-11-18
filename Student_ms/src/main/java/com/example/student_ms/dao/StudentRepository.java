package com.example.student_ms.dao;

import com.example.student_ms.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student,Long> {
    Optional<Student> findStudentByField(String field);
}
