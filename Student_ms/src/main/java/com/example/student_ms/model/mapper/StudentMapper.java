package com.example.student_ms.model.mapper;

import com.example.student_ms.model.Student;
import com.example.student_ms.model.dtos.StudentDto;
import com.example.student_ms.model.dtos.StudentRegistrationDto;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StudentMapper {

    @Autowired
    private ModelMapper modelMapper;

    public Student toEntity(StudentRegistrationDto dto) {
        return modelMapper.map(dto, Student.class);
    }

    public StudentDto toDTO(Student entity) {
        return modelMapper.map(entity, StudentDto.class);
    }

    public List<StudentDto> toDTOs(List<Student> students) {
        List<StudentDto> dtos = new ArrayList<>();
        for (Student student : students) {
            dtos.add(toDTO(student));
        }
        return dtos;
    }

//    public Student
}
