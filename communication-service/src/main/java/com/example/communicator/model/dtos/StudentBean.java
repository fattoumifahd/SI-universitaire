package com.example.communicator.model.dtos;

import com.example.communicator.model.enums.FieldOfStudy;
import lombok.*;

import java.time.LocalDate;


@Data
@Getter
@Setter
//@AllArgsConstructor
@NoArgsConstructor
public class StudentBean {
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private FieldOfStudy field;
    private String password;
    private String username;

    public StudentBean(Long id, String firstName, String lastName, LocalDate birthDate, FieldOfStudy field, String password, String username) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.field = field;
        this.password = password;
        this.username = username;
    }
}
