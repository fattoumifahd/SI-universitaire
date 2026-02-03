package com.example.coursms.model.dto;

import com.example.coursms.model.Module;
import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
@Getter
@Setter
public class SemesterDTO {
    private String name;
    private Long fieldId;
    private List<Module> modules;
    private LocalDate startDate;
    private LocalDate endDate;
}
