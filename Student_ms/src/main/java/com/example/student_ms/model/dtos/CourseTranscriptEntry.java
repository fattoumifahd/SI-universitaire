package com.example.student_ms.model.dtos;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
@Data
@Getter
@Setter
public class CourseTranscriptEntry {
    private String courseName;
    private String moduleName;
    private String semesterName;
    private LocalDate semesterStartDate;
    private LocalDate semesterEndDate;

    private Double continuousAssessment;
    private Double finalExam;
    private Double finalGrade;
    private Double courseCoefficient;
    private Double moduleCoefficient; // if needed

    private Integer academicYear;
    private Integer semesterNumber;
}