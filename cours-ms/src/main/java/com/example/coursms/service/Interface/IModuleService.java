package com.example.coursms.service.Interface;

import java.util.List;
import com.example.coursms.model.Module;

public interface IModuleService {
    Module createModule(Module module);

    Module updateModule(Long id, Module module);

    Module getModuleById(Long id);

    List<Module> getAllModules();

    List<Module> getModulesBySemester(Long semesterId);

    void deleteModule(Long id);
}
