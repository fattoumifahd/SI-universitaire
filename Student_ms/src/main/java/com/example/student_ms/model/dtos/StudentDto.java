package com.example.student_ms.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Data
@Getter
@Setter
public class StudentDto {
    @NotBlank(message = "first name field is required !")
    private String firstName;
    @NotBlank(message = "lastName field is required !")
    private String lastName;
    @NotBlank(message = "Field of Study is required ! ")
    private String field;
    @NotBlank(message = "Birth date field is required !")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate birthDate;
    private String password;
    private String username;

    @Override
    public String toString() {
       return "FistName: " +  firstName + ", LastName: " +  lastName+ "field: " +  field+ "birthDate: " +  birthDate.toString();
    }
}
