package com.example.coursms.model.mapper;

import com.example.coursms.model.Field;
import com.example.coursms.model.dto.FieldDTO;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class FieldMapper {

    private final ModelMapper modelMapper;

    public FieldDTO toDTO(Field field) {
        return modelMapper.map(field, FieldDTO.class);
    }

    public Field toEntity(FieldDTO dto) {
        return modelMapper.map(dto, Field.class);
    }
}
