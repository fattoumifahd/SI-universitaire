package com.example.coursms.dao;

import com.example.coursms.model.Field;
import com.example.coursms.model.enums.FieldOfStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FieldRepository extends JpaRepository<Field,Long> {
    Optional<Field> findFieldByFieldName(String fieldName);

    Field findByFieldName(FieldOfStudy fieldName);
}
