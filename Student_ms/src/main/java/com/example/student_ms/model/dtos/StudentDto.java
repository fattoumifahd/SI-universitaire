package com.example.student_ms.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class StudentDto {

    private String  firstName;
    private String lastName;
    private String field;
    private LocalDate birthDate;
    private String password;
    private String username;

    @Override
    public String toString() {
       return "FistName: " +  firstName + ", LastName: " +  lastName+ "field: " +  field+ "birthDate: " +  birthDate.toString();
    }
}
