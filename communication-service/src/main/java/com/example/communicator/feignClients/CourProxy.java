package com.example.communicator.feignClients;


import com.example.communicator.model.dtos.CourDto;
import com.example.communicator.model.dtos.CoursBean;
import jakarta.validation.Valid;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(name = "coursms", url = "http://localhost:8081/api/cours")
public interface CourProxy {
    @GetMapping
    List<CoursBean> getAllCours();

    @GetMapping("/{id}")
    CoursBean getCourById(@PathVariable Long id);

    @PostMapping("/")
    CoursBean createCour(@Valid @RequestBody CourDto courDto);

    @PutMapping("/{id}")
    CoursBean editCour(@PathVariable Long id, @Valid @RequestBody CourDto courDto);
}
