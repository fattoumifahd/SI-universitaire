package com.example.communicator.controller;

import com.example.communicator.feignClients.StudentCourseGradeProxy;
import com.example.communicator.model.dtos.CoursBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/student/details")
public class StudentCourseGradeController {
    @Autowired
    private StudentCourseGradeProxy studentCourseGradeProxy;

    @GetMapping("/{id}/cours")
    public ResponseEntity<List<CoursBean>> getCoursById(@PathVariable Long id) {
        return new ResponseEntity<>(studentCourseGradeProxy.getStudentCourses(id), HttpStatus.OK);
    }
}
