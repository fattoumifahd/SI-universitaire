package com.example.coursms.model;

import com.example.coursms.model.enums.FieldOfStudy;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Field {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private FieldOfStudy fieldName;

    private String description;

    @OneToMany(mappedBy = "fieldOfStudy" , fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Semester> semesters;
}
