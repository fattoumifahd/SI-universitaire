package com.example.communicator.feignClients;

import com.example.communicator.model.dtos.CoursBean;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "studentms-course-grade",
        url = "http://localhost:8080/api/student/details"
)
public interface StudentCourseGradeProxy {

    @GetMapping("/{id}/cours")
    List<CoursBean> getStudentCourses(@PathVariable("id") Long id);
}
