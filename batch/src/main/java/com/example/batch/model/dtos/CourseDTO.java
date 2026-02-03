package com.example.batch.model.dtos;

import lombok.Data;

@Data

public class CourseDTO {
    private Long id;
    private String name;
    private String description;
    private int hours;
    private double coefficient;
}
