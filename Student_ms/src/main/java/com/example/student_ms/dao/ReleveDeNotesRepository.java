package com.example.student_ms.dao;

import com.example.student_ms.model.ReleveDeNotesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ReleveDeNotesRepository extends JpaRepository<ReleveDeNotesEntity, Long> {
    Optional<ReleveDeNotesEntity> findByStudentId(long id);
}
