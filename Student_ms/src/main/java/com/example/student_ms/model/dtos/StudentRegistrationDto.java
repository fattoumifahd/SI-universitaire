package com.example.student_ms.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Getter
@Setter

public class StudentRegistrationDto extends  StudentDto {

    private Boolean hasEquivalence;
    private Integer equivalenceSemster;

}
