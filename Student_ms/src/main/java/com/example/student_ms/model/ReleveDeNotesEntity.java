package com.example.student_ms.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ReleveDeNotesEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long studentId;
    @Lob
    private String transcriptJson; // store as JSON string

    // or map as @ElementCollection if you normalize
}