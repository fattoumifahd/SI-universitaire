package com.example.coursms.dao;

import com.example.coursms.model.Field;
import com.example.coursms.model.Module;
import com.example.coursms.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SemesterRepository extends JpaRepository<Semester, Long> {
    List<Semester> findByFieldOfStudyId(Long fieldOfStudy);
    List<Semester> findByNameContainingIgnoreCase(String name);
}
