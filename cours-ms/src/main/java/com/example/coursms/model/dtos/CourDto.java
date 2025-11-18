package com.example.coursms.model.dtos;

import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter
public class CourDto {
    @NotBlank(message = "name is required !")
    private String name;
//    @NotBlank(message = "")
    private String description;
    @NotBlank(message = "Hours number is required !")
    @Size(min = 1, max = 2)
    private int hoursNumber;

}
