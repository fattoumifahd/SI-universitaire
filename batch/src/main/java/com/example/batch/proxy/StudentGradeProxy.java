package com.example.batch.proxy;

import com.example.batch.model.dtos.StudentGradeDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient(
        name = "student-grade",
        url = "http://localhost:8080/api/student/grade"
)
public interface StudentGradeProxy {
    @GetMapping("/grades/batch")
    List<StudentGradeDTO> getStudentGrades(@RequestParam Integer academicYear);
}
