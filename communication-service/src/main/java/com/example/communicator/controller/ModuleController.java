package com.example.communicator.controller;

import com.example.communicator.feignClients.ModuleProxy;
import com.example.communicator.model.dtos.ModuleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/module")
@RequiredArgsConstructor
public class ModuleController {

    private final ModuleProxy moduleProxy;

    @PostMapping
    public ResponseEntity<ModuleDTO> createModule(@RequestBody ModuleDTO module) {
        return ResponseEntity.ok(moduleProxy.createModule(module));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModuleDTO> updateModule(@PathVariable Long id,
                                                  @RequestBody ModuleDTO module) {
        return ResponseEntity.ok(moduleProxy.updateModule(id, module));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModuleDTO> getModuleById(@PathVariable Long id) {
        return ResponseEntity.ok(moduleProxy.getModuleById(id));
    }

    @GetMapping
    public ResponseEntity<List<ModuleDTO>> getAllModules() {
        return ResponseEntity.ok(moduleProxy.getAllModules());
    }

    @GetMapping("/semester/{semesterId}")
    public ResponseEntity<List<ModuleDTO>> getModulesBySemester(@PathVariable Long semesterId) {
        return ResponseEntity.ok(moduleProxy.getModulesBySemesterId(semesterId));
    }

    @GetMapping("/semester/{semesterName}/{fieldId}")
    public ResponseEntity<List<ModuleDTO>> getModulesBySemesterAndFieldId(
            @PathVariable String semesterName,
            @PathVariable Long fieldId
    ) {
        return ResponseEntity.ok(moduleProxy.getModulesBySemesterAndFieldId(semesterName, fieldId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteModule(@PathVariable Long id) {
        moduleProxy.deleteModule(id);
        return ResponseEntity.noContent().build();
    }
}