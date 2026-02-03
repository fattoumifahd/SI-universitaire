package com.example.student_ms.model.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@RequiredArgsConstructor
public class SemesterDTO {
    private String name;
    private Long fieldId;
    private List<ModuleDTO> modules;
    private LocalDate startDate;
    private LocalDate endDate;
}
