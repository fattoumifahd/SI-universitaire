package com.example.coursms.service;

import com.example.coursms.dao.SemesterRepository;
import com.example.coursms.exception.ResourceNotFoundException;
import com.example.coursms.model.Field;
import com.example.coursms.model.Semester;
import com.example.coursms.service.Interface.ISemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SemesterService implements ISemesterService {
    private final SemesterRepository semesterRepository;

    @Autowired
    public SemesterService(SemesterRepository semesterRepository) {
        this.semesterRepository = semesterRepository;
    }

    @Override
    public Semester createSemester(Semester semester) {
        return semesterRepository.save(semester);
    }

    @Override
    public Semester updateSemester(Long id, Semester updated) {
        Semester semester = getSemesterById(id);
        semester.setName(updated.getName());
        semester.setFieldOfStudy(updated.getFieldOfStudy());
        semester.setModules(updated.getModules());
        return semesterRepository.save(semester);
    }

    @Override
    public Semester getSemesterById(Long id) {
        return semesterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Semester not found with id " + id));
    }

    @Override
    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    @Override
    public List<Semester> getSemestersByField(Long fieldId) {
        return semesterRepository.findByFieldOfStudyId(fieldId);
    }

    @Override
    public void deleteSemester(Long id) {
        Semester semester = getSemesterById(id);
        semesterRepository.delete(semester);
    }
}
