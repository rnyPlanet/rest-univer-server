package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.TypeClasses;
import com.example.demo.repository.schedule.TypeClassesRepository;
import com.example.demo.service.schedule.TypeClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TypeClassesServiceImpl implements TypeClassesService {

    private final TypeClassesRepository typeClassesRepository;

    @Autowired
    public TypeClassesServiceImpl(TypeClassesRepository typeClassesRepository) {
        this.typeClassesRepository = typeClassesRepository;
    }

    @Override
    public List<TypeClasses> getAll() {
        List<TypeClasses> result = typeClassesRepository.findAll();
        log.info("IN typeClasses getAll: {}", result.size());
        return result;
    }

    @Override
    public TypeClasses findById(Long id) {
        TypeClasses result = typeClassesRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN typeClasses findById - no Classes found by id: {}", id);
            return null;
        }

        log.info("IN typeClasses findById: {} found by id: {}", result, id);
        return result;
    }

}
