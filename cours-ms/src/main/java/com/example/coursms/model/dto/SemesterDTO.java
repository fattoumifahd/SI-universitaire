package com.example.coursms.model.dto;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class SemesterDTO {
    private String semesterName;
    private Long fieldId;
    private List<Long> moduleIds;
    private LocalDate startDate;
    private LocalDate endDate;
}
