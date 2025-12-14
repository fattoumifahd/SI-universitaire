package com.example.coursms.model.mapper;

import com.example.coursms.model.dto.ModuleDTO;
import com.example.coursms.model.Module;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ModuleMapper {

    private final ModelMapper modelMapper;

    public ModuleDTO toDTO(Module module) {
        ModuleDTO dto = modelMapper.map(module, ModuleDTO.class);
        if (module.getCourses() != null) {
            dto.setCourseIds(
                    module.getCourses().stream()
                            .map(c -> c.getId())
                            .collect(Collectors.toList())
            );
        }
        return dto;
    }

    public Module toEntity(ModuleDTO dto) {
        return modelMapper.map(dto, Module.class);
    }
}
