package com.example.coursms.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourDTO {
    @NotBlank(message = "name is required !")
    private String name;
//    @NotBlank(message = "")
    private String description;


    private int hours;
    private double coefficient;

}
