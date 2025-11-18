package com.example.student_ms.model;

import com.example.student_ms.model.enums.FieldsOfStudies;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    @Enumerated(EnumType.STRING)
    private FieldsOfStudies field;
    private String password;
    private String username;


}
