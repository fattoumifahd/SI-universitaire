package com.example.coursms.service;

import com.example.coursms.dao.CoursRepository;
import com.example.coursms.model.Cour;
import com.example.coursms.model.dtos.CourDto;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    public Cour create(CourDto cour)  {
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
    public Cour update(Long id, CourDto courDto) {
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
}
