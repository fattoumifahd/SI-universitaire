package com.example.coursms.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class ModuleDTO {
//    private Long id;
    @NotNull
    private String moduleName;
    @NotNull
    private String moduleDescription;
    private List<Long> courseIds;
}
