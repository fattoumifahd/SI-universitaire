package com.example.coursms.dao;

import com.example.coursms.model.Cour;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CoursRepository extends JpaRepository<Cour, Long> {
    Optional<Cour> findByName(String name);
}
