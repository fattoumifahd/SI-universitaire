package com.example.communicator.feignClients;

import com.example.communicator.model.dtos.StudentBean;
import com.example.communicator.model.dtos.StudentDto;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "studentms", url = "http://localhost:8080/api/student")
public interface StudentProxy {
    @GetMapping("/{id}")
    public StudentBean getStudent(@PathVariable Long id);

    @PostMapping("/create")
    public StudentBean createStudent(@Valid @RequestBody StudentDto studentDto);

    @PutMapping("/update/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Validated @RequestBody StudentDto studentDto);

    @DeleteMapping("/remove/{id}")
    public ResponseEntity<?> deleteStudent(@PathVariable Long id);
}
