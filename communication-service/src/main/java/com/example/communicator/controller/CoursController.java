package com.example.communicator.controller;

import com.example.communicator.feignClients.CourProxy;
import com.example.communicator.model.dtos.CourDto;
import com.example.communicator.model.dtos.CoursBean;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/cours")
@RequiredArgsConstructor
public class CoursController {
    @Autowired
    private CourProxy courProxy;
    @GetMapping
    public ResponseEntity<List<CoursBean>> getAllCours() {
        List<CoursBean> cours = courProxy.getAllCours();
        return ResponseEntity.ok(cours);
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<CoursBean> getCourById(@PathVariable Long id) {
        CoursBean cours = courProxy.getCourById(id);
        return ResponseEntity.ok(cours);
    }

    // CREATE
    @PostMapping()
    public ResponseEntity<CoursBean> createCour(@Valid @RequestBody CourDto courDto) {
        CoursBean created = courProxy.createCour(courDto);
        return ResponseEntity.ok(created);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<CoursBean> editCour(
            @PathVariable Long id,
            @Valid @RequestBody CourDto courDto
    ) {
        CoursBean updated = courProxy.editCour(id, courDto);
        return ResponseEntity.ok(updated);
    }
}
