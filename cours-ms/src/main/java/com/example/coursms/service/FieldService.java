package com.example.coursms.service;

import com.example.coursms.dao.FieldRepository;
import com.example.coursms.exception.ResourceNotFoundException;
import com.example.coursms.model.Field;
import com.example.coursms.model.enums.FieldOfStudy;
import com.example.coursms.service.Interface.IFieldService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FieldService implements IFieldService {

    private final FieldRepository fieldRepository;


//    @Autowired
//    public FieldService(FieldRepository fieldRepository) {
//        this.fieldRepository = fieldRepository;
//    }
    @Override
    public Field createField(Field field) {
        return fieldRepository.save(field);
    }

    @Override
    public Field updateField(Long id, Field updated) {
        Field field = getFieldById(id);
        field.setFieldName(updated.getFieldName());
        return fieldRepository.save(field);
    }

    @Override
    public Field getFieldById(Long id) {
        return fieldRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Field not found with id " + id));
    }

    @Override
    public List<Field> getAllFields() {
        return fieldRepository.findAll();
    }

    @Override
    public Field getFieldByName(String name) {
        Optional<Field> field = fieldRepository.findFieldByFieldName(name);
        if (field.isEmpty()) {
            throw new ResourceNotFoundException("Field not found with name " + name);
        }
        return field.get();
    }

    @Override
    public void deleteField(Long id) {
        Field field = getFieldById(id);
        fieldRepository.delete(field);
    }
}
