package com.example.communicator.controller;

import com.example.communicator.feignClients.StudentProxy;
import com.example.communicator.model.dtos.StudentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {
    @Autowired
    private StudentProxy studentProxy;

    @GetMapping("/{id}")
    public ResponseEntity<?> getStudent(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(studentProxy.getStudent(id));

        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createStudent(@RequestBody StudentDto studentDto) {
        try {
            return ResponseEntity.ok(studentProxy.createStudent(studentDto));
        }catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

}
