package com.example.communicator.controller;

import com.example.communicator.feignClients.SemesterProxy;
import com.example.communicator.model.dtos.SemesterDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/semester")
@RequiredArgsConstructor
public class SemesterController {

    private final SemesterProxy semesterProxy;

    @PostMapping
    public ResponseEntity<SemesterDTO> createSemester(@RequestBody SemesterDTO semester) {
        return ResponseEntity.ok(semesterProxy.createSemester(semester));
    }

    @PutMapping("/{id}")
    public ResponseEntity<SemesterDTO> updateSemester(@PathVariable Long id,
                                                      @RequestBody SemesterDTO semester) {
        return ResponseEntity.ok(semesterProxy.updateSemester(id, semester));
    }

    @GetMapping("/{id}")
    public ResponseEntity<SemesterDTO> getSemesterById(@PathVariable Long id) {
        return ResponseEntity.ok(semesterProxy.getSemesterById(id));
    }

    @GetMapping
    public ResponseEntity<List<SemesterDTO>> getAllSemesters() {
        return ResponseEntity.ok(semesterProxy.getAllSemesters());
    }

    @GetMapping("/field/{fieldId}")
    public ResponseEntity<List<SemesterDTO>> getSemestersByField(@PathVariable Long fieldId) {
        return ResponseEntity.ok(semesterProxy.getSemestersByField(fieldId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSemester(@PathVariable Long id) {
        semesterProxy.deleteSemester(id);
        return ResponseEntity.noContent().build();
    }
}