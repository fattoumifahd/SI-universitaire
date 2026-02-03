package com.example.student_ms.model.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@AllArgsConstructor
public class StudentDto {
    private Long id;

    @NotBlank(message = "first name field is required !")
    private String firstName;
    @NotBlank(message = "lastName field is required !")
    private String lastName;
    @NotBlank(message = "Field of Study is required ! ")
    private String field;
//    @NotBlank(message = "Birth date field is required !")
    private LocalDate birthDate;
    private String username;
    private Integer currentSemester;

    @Override
    public String toString() {
       return "FistName: " +  firstName + ", LastName: " +  lastName+ "field: " +  field+ "birthDate: " +  birthDate.toString();
    }

    public StudentDto() {
        this.firstName = "";
        this.lastName = "";
        this.field = "";
        this.birthDate = null;
    }
}
