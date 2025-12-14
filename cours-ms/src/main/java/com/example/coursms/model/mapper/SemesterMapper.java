package com.example.coursms.model.mapper;

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
        SemesterDTO dto = modelMapper.map(semester, SemesterDTO.class);
        dto.setFieldId(semester.getFieldOfStudy().getId());

        if (semester.getModules() != null) {
            dto.setModuleIds(
                    semester.getModules().stream()
                            .map(m -> m.getId())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Semester toEntity(SemesterDTO dto) {
        return modelMapper.map(dto, Semester.class);
    }
}