package com.example.student_ms.proxy;


import com.example.student_ms.model.dtos.CoursBean;
import com.example.student_ms.model.dtos.CoursDTO;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "coursms-courses", url = "http://localhost:8081/api/cours")
public interface CourProxy {
    @GetMapping
    List<CoursBean> getAllCours();

    @GetMapping("/{id}")
    CoursBean getCourById(@PathVariable Long id);

    @PostMapping("")
    CoursBean createCour(@Valid @RequestBody CoursDTO courDto);

    @PutMapping("/{id}")
    CoursBean editCour(@PathVariable Long id, @Valid @RequestBody CoursDTO courDto);

    @GetMapping("/module/{moduleId}")
    List<CoursDTO> getCoursByModuleId(@PathVariable Long moduleId);
}
