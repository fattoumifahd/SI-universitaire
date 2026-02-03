package com.example.student_ms.model.dtos;

import com.example.student_ms.model.enums.FieldsOfStudies;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class ReleveDeNotes {
    private Long studentId;
    private String studentFullName;
    private FieldsOfStudies fieldOfStudy;
    private Integer currentSemester;

    // Grouped by academic year or semester (optional)
    private List<CourseTranscriptEntry> entries = new ArrayList<>();

    // Optional: overall GPA
    private Double overallWeightedAverage;
}