package com.example.student_ms.model.mapper;

import com.example.student_ms.model.Student;
import com.example.student_ms.model.dtos.StudentDto;
import com.example.student_ms.model.dtos.StudentRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Student toEntity(StudentRegistrationDto dto) {
        return modelMapper.map(dto, Student.class);
    }
}
