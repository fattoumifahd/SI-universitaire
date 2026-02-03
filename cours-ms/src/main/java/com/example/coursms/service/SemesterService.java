package com.example.coursms.service;

import com.example.coursms.dao.FieldRepository;
import com.example.coursms.dao.SemesterRepository;
import com.example.coursms.exception.ResourceNotFoundException;
import com.example.coursms.model.Field;
import com.example.coursms.model.Semester;
import com.example.coursms.model.dto.SemesterDTO;
import com.example.coursms.model.mapper.SemesterMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SemesterService {

    private  SemesterRepository semesterRepository;
//    @Autowired
    private FieldRepository fieldRepository;
//    @Autowired
    private  SemesterMapper semesterMapper;

//    @Autowired
    public SemesterService(SemesterRepository semesterRepository, FieldRepository fieldRepository, SemesterMapper semesterMapper) {
        this.semesterRepository = semesterRepository;
        this.fieldRepository = fieldRepository;
        this.semesterMapper = semesterMapper;
    }

    public Semester createSemester(SemesterDTO dto) {
        Semester semester = semesterMapper.toEntity(dto, fieldRepository.findById(dto.getFieldId()).get());
        return semesterRepository.save(semester);
    }

    public Semester updateSemester(Long id, SemesterDTO updated) {
        Semester semester = semesterMapper.toEntity(getSemesterById(id), fieldRepository.findById(updated.getFieldId()).get());
        semester.setName(updated.getName());
        semester.setFieldOfStudy(fieldRepository.findById(updated.getFieldId()).get());
        semester.setModules(updated.getModules());
        return semesterRepository.save(semester);
    }

    public List<SemesterDTO> getSemesterNameByFieldId(String semesterName, Long fieldId) {
        List<Semester> semesters = semesterRepository.findByNameContainingIgnoreCase(semesterName);
        List<SemesterDTO> semesterDTOS = new ArrayList<>();
        semesters.stream().filter( s -> s.getFieldOfStudy().getId().equals(fieldId)).collect(Collectors.toList()).
                forEach(semester ->  semesterDTOS.add(semesterMapper.toDTO(semester)));
        return semesterDTOS;
    }

//    @Override
//    public Semester updateSemester(Long id, SemesterDTO updated) {
//        SemesterDTO semester = getSemesterById(id);
//        Semester semester = semesterMapper
//                .toEntity(getSemesterById(id)) ;
//        semester.setName(updated.getName());
////        semester.getFieldOfStudy(updated.getFieldOfStudy());
////        semester.setModules(updated.getModules());
//        return semesterRepository.save(semester);
//    }

    public SemesterDTO getSemesterById(Long id) {
        Semester semester = semesterRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Semester not found with id " + id));
        return semesterMapper.toDTO(semester);
    }

    public List<Semester> getAllSemesters() {
        return semesterRepository.findAll();
    }

    public List<SemesterDTO> getSemestersByField(Long fieldId) {
        List<Semester> semester =semesterRepository.findByFieldOfStudyId(fieldId);
        List<SemesterDTO> dtos = new ArrayList<>();
        for (Semester s : semester) {
            dtos.add(semesterMapper.toDTO(s));
        }
        return dtos;

    }

    public void deleteSemester(Long id) {
        Field field = semesterRepository.findById(id).get().getFieldOfStudy();
        Semester semester = semesterMapper.toEntity(getSemesterById(id), field);
        semesterRepository.delete(semester);
    }
}
