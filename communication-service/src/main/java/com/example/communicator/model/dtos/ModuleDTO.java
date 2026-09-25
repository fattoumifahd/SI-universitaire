package com.example.communicator.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class ModuleDTO {
    private String moduleName;
    private String moduleDescription;
    private List<Long> courseIds;
}
