package com.example.communicator.controller;

import com.example.communicator.feignClients.FieldProxy;
import com.example.communicator.model.dtos.FieldDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/field")
@RequiredArgsConstructor
public class FieldController {

    private final FieldProxy fieldProxy;

    @PostMapping
    public ResponseEntity<FieldDTO> createField(@RequestBody FieldDTO field) {
        return ResponseEntity.ok(fieldProxy.createField(field));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FieldDTO> updateField(@PathVariable Long id,
                                                @RequestBody FieldDTO field) {
        return ResponseEntity.ok(fieldProxy.updateField(id, field));
    }

    @GetMapping("/{id}")
    public ResponseEntity<FieldDTO> getFieldById(@PathVariable Long id) {
        return ResponseEntity.ok(fieldProxy.getFieldById(id));
    }

    @GetMapping
    public ResponseEntity<List<FieldDTO>> getAllFields() {
        return ResponseEntity.ok(fieldProxy.getAllFields());
    }

    @GetMapping("/by-name/{name}")
    public ResponseEntity<FieldDTO> getFieldByName(@PathVariable String name) {
        return ResponseEntity.ok(fieldProxy.getFieldByName(name));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteField(@PathVariable Long id) {
        fieldProxy.deleteField(id);
        return ResponseEntity.noContent().build();
    }
}

