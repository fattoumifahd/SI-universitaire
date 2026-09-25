package com.example.communicator.model.dtos;

import com.example.communicator.model.enums.FieldOfStudy;
import lombok.*;

import java.time.LocalDate;

@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class StudentDto {
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private FieldOfStudy field;
    private String password;
    private String username;
}
