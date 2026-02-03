package com.example.coursms.model.mapper;

import com.example.coursms.model.Field;
import com.example.coursms.model.Semester;
import com.example.coursms.model.dto.SemesterDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class SemesterMapper {

    private final ModelMapper modelMapper;

    public SemesterDTO toDTO(Semester semester) {
//        SemesterDTO dto = modelMapper.map(semester, SemesterDTO.class);
        SemesterDTO dto = new  SemesterDTO();
//        dto.setFieldId(semester.getFieldOfStudy().getId());
//
//        if (semester.getModules() != null) {
//            dto.setModuleIds(
//                    semester.getModules().stream()
//                            .map(m -> m.getId())
//                            .collect(Collectors.toList())
//            );
//        }
//        return dto;
        dto.setName(semester.getName());
        dto.setStartDate(semester.getStartDate());
        dto.setEndDate(semester.getEndDate());
        dto.setModules(semester.getModules());

        if (semester.getFieldOfStudy() != null) {
            dto.setFieldId(semester.getFieldOfStudy().getId());
        }
        return dto;
    }

    public Semester toEntity(SemesterDTO dto, Field field) {
        Semester semester = new Semester();
//        semester.setId(dto.getFieldId());
        semester.setName(dto.getName());
        semester.setStartDate(dto.getStartDate());
        semester.setEndDate(dto.getEndDate());
        semester.setFieldOfStudy(field); // fetched by ID
        return semester;
    }
}