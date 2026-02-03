package com.example.student_ms.model.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CoursDTO {
    @NotBlank(message = "name is required !")
    private String name;
//    @NotBlank(message = "")
    private String description;
    @NotBlank(message = "Hours number is required !")
    private int hours;
    private double coefficient;
}
