package com.example.student_ms.proxy;

import com.example.student_ms.model.dtos.SemesterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "coursms",
        url = "http://localhost:8081/api/semester"   // example: http://localhost:8081
)
public interface SemesterProxy {
    @PostMapping("/api/semester")
    SemesterDTO createSemester(@RequestBody SemesterDTO semester);

    @PutMapping("/api/semester/{id}")
    SemesterDTO updateSemester(@PathVariable("id") Long id,
                               @RequestBody SemesterDTO semester);

    @GetMapping("/api/semester/{id}")
    SemesterDTO getSemesterById(@PathVariable("id") Long id);

    @GetMapping("/api/semester")
    List<SemesterDTO> getAllSemesters();

    @GetMapping("/api/semester/field/{fieldId}")
    List<SemesterDTO> getSemestersByField(@PathVariable("fieldId") Long fieldId);

    @DeleteMapping("/api/semester/{id}")
    void deleteSemester(@PathVariable("id") Long id);
}
