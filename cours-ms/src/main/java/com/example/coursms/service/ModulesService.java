package com.example.coursms.service;

import com.example.coursms.dao.ModuleRepository;
import com.example.coursms.exception.ResourceNotFoundException;
import com.example.coursms.model.Module;
import com.example.coursms.service.Interface.IModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ModulesService  implements IModuleService {
    private final ModuleRepository moduleRepository;

    @Autowired
    public ModulesService(ModuleRepository moduleRepository) {
        this.moduleRepository = moduleRepository;
    }

//    public List<Module> findAll() {
//        return moduleRepository.findAll().stream().toList();
//    }

//    public List<Module> findModuleByCourses(List<Cour> courses) {
//        return moduleRepository.findAll().stream().filter(
//                module -> module.getCourses().stream()
//                        .forEach(
//                                cour -> cour.getId().equals())));
//    }
    @Override
    public Module createModule(Module module) {
        return moduleRepository.save(module);
    }

    @Override
    public Module updateModule(Long id, Module updated) {
        Module module = getModuleById(id);
        module.setModuleName(updated.getModuleName());
        module.setModuleDescription(updated.getModuleDescription());
        module.setCourses(updated.getCourses());
        return moduleRepository.save(module);
    }

    @Override
    public Module getModuleById(Long id) {
        return moduleRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Module not found with id " + id));
    }

    @Override
    public List<Module> getAllModules() {
        return moduleRepository.findAll();
    }

    @Override
    public List<Module> getModulesBySemester(Long semesterId) {
        return moduleRepository.findBySemesterId(semesterId);
    }

    @Override
    public void deleteModule(Long id) {
        Module module = getModuleById(id);
        moduleRepository.delete(module);
    }
}
