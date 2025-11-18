package com.example.student_ms.rest;

import com.example.student_ms.model.Student;
import com.example.student_ms.model.dtos.StudentDto;
import com.example.student_ms.services.StudentService;
import jakarta.validation.Valid;
import jdk.javadoc.doclet.Reporter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.xml.validation.Validator;
import java.util.Optional;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    private final StudentService studentService;
    private final ModelMapper modelMapper;

    @Autowired
    public StudentController(StudentService studentService, ModelMapper modelMapper) {
        this.studentService = studentService;
        this.modelMapper = modelMapper;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Student> getStudent(@PathVariable Long id) {
        Optional<Student> student = studentService.findById(id);
        return student.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }


    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@Valid @RequestBody StudentDto studentDto) {
        studentService.create(studentDto);
        return ResponseEntity.ok(HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody StudentDto studentDto) {
        try {
            Student student =  studentService.update(id, studentDto);
            return ResponseEntity.ok(modelMapper.map(student, StudentDto.class));
        } catch (Exception e) {
            return new ResponseEntity<String>("Error While update student", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id) {
        try {
            studentService.deleteById(id);
            return new ResponseEntity<>("Student with id " + id + " has been deleted", HttpStatus.ACCEPTED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error While delete student", HttpStatus.BAD_REQUEST);
        }
    }
}
