package com.example.coursms.rest;

import com.example.coursms.model.Cour;
import com.example.coursms.model.dtos.CourDto;
import com.example.coursms.service.CoursService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cours")
public class CoursController {

    @Autowired
    private CoursService coursService;

    @GetMapping()
    public ResponseEntity<List<Cour>> getAllCours() {
        return new ResponseEntity<>(coursService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cour> getCourById(@PathVariable Long id) {
        Cour  cour = coursService.findById(id);
        if (cour == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(cour, HttpStatus.OK);
        }
    }

    @PostMapping("/create")
    public ResponseEntity<?> createCour(@Valid @RequestBody CourDto courDto) {
        coursService.create(courDto);
        return ResponseEntity.ok("Cour Has been Created");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> editCour(@PathVariable Long id, @Valid @RequestBody CourDto courDto) {
        Cour cour = coursService.update(id, courDto);
        if (cour == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        } else {
            return new ResponseEntity<>(cour, HttpStatus.OK);
        }
    }
}
