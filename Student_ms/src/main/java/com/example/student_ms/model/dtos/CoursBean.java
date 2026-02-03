package com.example.student_ms.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursBean {
    private Long id;
    private String name;
    private String description;
    private int hours;
    private double coefficient;
    private Long moduleId;
}
