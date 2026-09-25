package com.example.communicator.model.dtos;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class CourDto {
    @NotBlank(message = "name is required !")
    private String name;
//    @NotBlank(message = "")
    private String description;
    @NotBlank(message = "Hours number is required !")
    @Min(value = 12 , message = "Hours number must be at least 12H !")
    @Max(value = 150, message = "Hours number cannot exceed 150H !")
    private int hours;
    private double coefficient;


}
