package com.example.communicator.feignClients;

import com.example.communicator.model.dtos.FieldDTO;
import com.example.communicator.model.enums.FieldOfStudy;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(
        name = "coursms",
        path = "/api/field"
)
public interface FieldProxy {

    @GetMapping("/{id}")
    FieldDTO getFieldById(@PathVariable Long id);

    @PostMapping
    FieldDTO createField(@RequestBody FieldDTO field);

    @PutMapping("/{id}")
    FieldDTO updateField(
            @PathVariable("id") Long id,
            @RequestBody FieldDTO field
    );

    @GetMapping
    List<FieldDTO> getAllFields();

    @GetMapping("/by-name/{name}")
    FieldDTO getFieldByName(@PathVariable String name);

    @DeleteMapping("/{id}")
    void deleteField(@PathVariable("id") Long id);
}