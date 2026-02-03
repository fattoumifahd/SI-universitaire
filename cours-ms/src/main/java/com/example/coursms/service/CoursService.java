package com.example.coursms.service;

import com.example.coursms.dao.CoursRepository;
import com.example.coursms.model.Cour;
import com.example.coursms.model.dto.CourDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CoursService {
    private final CoursRepository coursRepository;
    private final ModelMapper modelMapper;
    public CoursService(CoursRepository coursRepository, ModelMapper modelMapper) {
        this.coursRepository = coursRepository;
        this.modelMapper = modelMapper;
    }

    public List<Cour> findAll() {
        return coursRepository.findAll();
    }

    public Cour create(CourDTO cour)  {
        Cour coursEntity = modelMapper.map(cour, Cour.class);
        coursRepository.save(coursEntity);
        return coursEntity;
    }

    public Cour findById(Long id)  {
        Optional<Cour> cours = coursRepository.findById(id);
        if (cours.isPresent()) {
            return cours.get();
        } else {
            return null;
        }
    }
    public Cour update(Long id, CourDTO courDto) {
        Optional<Cour> cours = coursRepository.findById(id);
        if (cours.isPresent()) {
            Cour cour = modelMapper.map(cours.get(), Cour.class);
            coursRepository.save(cour);
            return cour;
        } else {
            return null;
        }
    }

    public void deleteById(Long id) throws RuntimeException {
        if (coursRepository.findById(id).isPresent()) {
            coursRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cour Not Found !");
        }

    }

    public List<CourDTO> getCoursesByModuleId(Long moduleId) {
        List<Cour> courses = coursRepository.findAll().
                stream().filter(c -> c.getModule().getId().equals(moduleId)).collect(Collectors.toList());
        List<CourDTO> courDTOS = new ArrayList<>();
        for (Cour cour : courses) {
            CourDTO courDTO = modelMapper.map(cour, CourDTO.class);
            courDTOS.add(courDTO);
        }
        return courDTOS;
    }
}
