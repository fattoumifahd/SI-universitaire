package com.example.batch.model.dtos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class StudentGradeDTO {
    private Long studentId;
    private Long courseId;

    private Double continuousAssessment;
    private Double finalExam;
    private Double finalGrade;

    private Integer academicYear;
    private Integer semesterNumber;
}
