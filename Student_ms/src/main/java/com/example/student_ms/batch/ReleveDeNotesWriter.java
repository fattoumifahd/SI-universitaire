package com.example.student_ms.batch;

import com.example.student_ms.model.ReleveDeNotesEntity;
import com.example.student_ms.dao.ReleveDeNotesRepository;
import com.example.student_ms.model.dtos.ReleveDeNotes;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;


@Component
public class ReleveDeNotesWriter implements ItemWriter<ReleveDeNotes> {

    private final ReleveDeNotesRepository repository;
    private final ObjectMapper objectMapper; // Use constructor injection

    public ReleveDeNotesWriter(ReleveDeNotesRepository repository, ObjectMapper objectMapper) {
        this.repository = repository;
        this.objectMapper = objectMapper;
    }

    @Override
    public void write(Chunk<? extends ReleveDeNotes> chunk) throws Exception {
        List<ReleveDeNotesEntity> entities = chunk.getItems().stream()
                .map(this::toEntity)
                .collect(Collectors.toList());

        repository.saveAll(entities);
    }

    private ReleveDeNotesEntity toEntity(ReleveDeNotes dto) {
        ReleveDeNotesEntity entity = new ReleveDeNotesEntity();
        entity.setStudentId(dto.getStudentId());
        try {
            entity.setTranscriptJson(objectMapper.writeValueAsString(dto));
        } catch (Exception e) {
            throw new RuntimeException("Failed to serialize ReleveDeNotes", e);
        }
        return entity;
    }
}
