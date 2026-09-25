package com.example.communicator.model.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CoursBean {
    private String name;
    private String description;
    private int hours;
    private double coefficient;

}
