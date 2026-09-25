package com.example.communicator.feignClients;

import com.example.communicator.model.dtos.SemesterDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "coursms",
        url = "http://localhost:8081/api/semester"
)
public interface SemesterProxy {
    @PostMapping()
    SemesterDTO createSemester(@RequestBody SemesterDTO semester);

    @PutMapping("/{id}")
    SemesterDTO updateSemester(@PathVariable("id") Long id,
                               @RequestBody SemesterDTO semester);

    @GetMapping("/{id}")
    SemesterDTO getSemesterById(@PathVariable("id") Long id);

    @GetMapping()
    List<SemesterDTO> getAllSemesters();

    @GetMapping("/field/{fieldId}")
    List<SemesterDTO> getSemestersByField(@PathVariable("fieldId") Long fieldId);

    @DeleteMapping("/{id}")
    void deleteSemester(@PathVariable("id") Long id);
}
