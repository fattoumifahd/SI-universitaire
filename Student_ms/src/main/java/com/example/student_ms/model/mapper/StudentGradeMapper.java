package com.example.student_ms.model.mapper;

import com.example.student_ms.model.StudentCourseGrade;
import com.example.student_ms.model.dtos.StudentGradeDTO;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StudentGradeMapper {
    @Autowired
    private  ModelMapper modelMapper ;

    public StudentCourseGrade toEntity(StudentGradeDTO studentGradeDTO){
        StudentCourseGrade grade = new StudentCourseGrade();
        grade.setStudentId(studentGradeDTO.getStudentId());
        grade.setCourseId(studentGradeDTO.getCourseId());
        grade.setFinalGrade(studentGradeDTO.getFinalGrade());
        grade.setSemesterNumber(studentGradeDTO.getSemesterNumber());
        grade.setAcademicYear(studentGradeDTO.getAcademicYear());
        grade.setContinuousAssessment(studentGradeDTO.getContinuousAssessment());
        grade.setFinalExam(studentGradeDTO.getFinalExam());
        return grade ;
    }
    public StudentGradeDTO toDTO(StudentCourseGrade studentGrade){
        return modelMapper.map(studentGrade,StudentGradeDTO.class);
    }
}
