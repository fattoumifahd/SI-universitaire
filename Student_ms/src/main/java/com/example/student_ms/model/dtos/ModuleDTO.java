package com.example.student_ms.model.dtos;

import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ModuleDTO {
    private String moduleName;
    @NotNull
    private String moduleDescription;
    private double moduleCoefficient;
    private ArrayList<CoursBean> courses;
    private Long SemesterId;



}
