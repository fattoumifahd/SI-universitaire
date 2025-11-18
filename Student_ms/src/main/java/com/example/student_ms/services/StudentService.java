package com.example.student_ms.services;

import com.example.student_ms.dao.StudentRepository;
import com.example.student_ms.model.Student;
import com.example.student_ms.model.dtos.StudentDto;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StudentService {
    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private static StudentRepository studentRepository;
    private final ModelMapper modelMapper;

    //    @Autowired
    public StudentService(StudentRepository studentRepository, ModelMapper mapper) {
        this.studentRepository = studentRepository;
        this.modelMapper = mapper;
    }

    public StudentDto create(StudentDto studentDto)  {
        logger.info(studentDto.toString());
        Student student = modelMapper.map(studentDto, Student.class);
        logger.info(student.toString());
        studentRepository.save(student);
        return studentDto;

    }

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
}
