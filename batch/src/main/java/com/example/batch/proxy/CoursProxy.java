package com.example.batch.proxy;


import com.example.batch.model.dtos.CourseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "course-service", url = "http://localhost:8081/api/cours")
public interface CoursProxy {
    @GetMapping
    List<CourseDTO> getAllCourses();
}