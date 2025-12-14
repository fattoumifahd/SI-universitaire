package com.example.coursms.service.Interface;

import com.example.coursms.model.Field;
import com.example.coursms.model.enums.FieldOfStudy;

import java.util.List;

public interface IFieldService {

    Field createField(Field field);

    Field updateField(Long id, Field field);

    Field getFieldById(Long id);

    List<Field> getAllFields();

    Field getFieldByName(String name);

    void deleteField(Long id);
}
