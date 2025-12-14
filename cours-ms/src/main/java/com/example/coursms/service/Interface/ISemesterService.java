package com.example.coursms.service.Interface;

import com.example.coursms.model.Field;
import com.example.coursms.model.Semester;

import java.util.List;

public interface ISemesterService {
    Semester createSemester(Semester semester);

    Semester updateSemester(Long id, Semester semester);

    Semester getSemesterById(Long id);

    List<Semester> getAllSemesters();

    List<Semester> getSemestersByField(Long fieldId);

    void deleteSemester(Long id);
}
