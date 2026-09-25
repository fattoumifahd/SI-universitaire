package com.example.communicator.feignClients;

import com.example.communicator.model.dtos.ModuleDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "coursms",
        url = "http://localhost:8081/api/module"
)
public interface ModuleProxy {

    @GetMapping("/{id}")
    ModuleDTO getModuleById(@PathVariable Long id);
    @PostMapping
    ModuleDTO createModule(ModuleDTO moduleDTO);
    @GetMapping
    List<ModuleDTO> getAllModules();

    @PutMapping("/{id}")
    ModuleDTO updateModule(@PathVariable Long id, @RequestBody ModuleDTO moduleDTO);

    @GetMapping("/semester/{semesterId}")
    List<ModuleDTO> getModulesBySemesterId(@PathVariable Long semesterId);

    @GetMapping("/semester/{semesterName}/{fieldId}")
    List<ModuleDTO> getModulesBySemesterAndFieldId(@PathVariable String semesterName,  @PathVariable Long fieldId );

    @DeleteMapping("/{id}")
    Void deleteModule(@PathVariable Long id);
}