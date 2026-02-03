package com.example.student_ms.services;

import com.example.student_ms.dao.StudentRepository;
import com.example.student_ms.model.Student;
import com.example.student_ms.model.dtos.*;
import com.example.student_ms.model.mapper.StudentMapper;
import com.example.student_ms.proxy.ModuleProxy;
import com.example.student_ms.proxy.SemesterProxy;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private SemesterProxy semesterService;
    @Autowired
    private ModuleProxy moduleService;

    private final Logger log = LoggerFactory.getLogger(StudentService.class);
    //    @Autowired
//    public StudentService(StudentRepository studentRepository, ModelMapper mapper, StudentMapper studentMapper) {
//        this.studentRepository = studentRepository;
//        this.modelMapper = mapper;
//        this.studentMapper = studentMapper;
//    }

    public Student studentRegistration(StudentRegistrationDto dto) {
        Student student = studentMapper.toEntity(dto);
        if(!dto.getHasEquivalence()) {
            student.setCurrentSemester(1);
            student.setValidatedSemesters(Collections.emptyList());
            return studentRepository.save(student);
        }

        int targetSemester = dto.getEquivalenceSemester();
        List<Integer> validatedSemester = new ArrayList<>();
        for (int i = 1; i < targetSemester; i++) {
            validatedSemester.add(i);
        }
        student.setValidatedSemesters(validatedSemester);
        student.setCurrentSemester(targetSemester);
        return studentRepository.save(student);
    }

//    public Student create(StudentDto studentDto)  {
//        logger.info(studentDto.toString());
//        Student student = modelMapper.map(studentDto, Student.class);
//        logger.info(student.toString());
//        studentRepository.save(student);
//        return student;
//
//    }

    public Student update(Long id, StudentDto studentDto) {
        Optional<Student> student = studentRepository.findById(id);
        if (student.isPresent()) {
            Student entity = student.get();
            entity = modelMapper.map(studentDto, Student.class);
            studentRepository.save(entity);
            return entity;
        } else {
            return null;
        }

    }

    public Optional<Student> findById(Long id) {
        return studentRepository.findById(id);

    }

    public void deleteById(Long id) throws Exception {
        studentRepository.deleteById(id);
    }

    public List<CoursBean> getCoursesBySemester(Long semesterId) {

        SemesterDTO semesterDTO = this.getSemester(semesterId);
        logger.info(semesterDTO.toString());
        List<ModuleDTO> modules = moduleService.getModulesBySemesterAndFieldId(semesterDTO.getName(),semesterDTO.getFieldId());

        return modules.stream().flatMap(m -> m.getCourses().stream()).collect(Collectors.toList());
//

//        List<ModuleDTO> modules =  moduleService.getModulesBySemesterId(semesterId);
//        return modules.stream().flatMap(m -> m.getCourses().stream())
//                .collect(Collectors.toList());


    }

    public SemesterDTO getSemester(Long semesterId) {

        return semesterService.getSemesterById(semesterId);

    }

    public List<StudentDto> getAllStudents() {
        return studentMapper.toDTOs(studentRepository.findAll()) ;
    }
 }
