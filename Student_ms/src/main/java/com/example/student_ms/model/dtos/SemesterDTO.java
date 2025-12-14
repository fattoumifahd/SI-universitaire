package com.example.student_ms.model.dtos;

import lombok.Data;

import java.util.List;

@Data
public class SemesterDTO {
    private String semesterName;
    private Long fieldId;
    private List<Long> moduleIds;
}
