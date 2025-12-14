package com.example.coursms.dao;

import com.example.coursms.model.Cour;
import com.example.coursms.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ModuleRepository extends JpaRepository<Module ,Long> {
    Optional<Module> findModuleByCourses(List<Cour> courses);
    Optional<List<Module>> findModuleBySemesterId(Long semesterId);

    List<Module> findBySemesterId(Long semesterId);
}
