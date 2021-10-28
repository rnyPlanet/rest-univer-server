package com.example.demo.service.schedule;

import com.example.demo.model.schedule.TypeClasses;

import java.util.List;

public interface TypeClassesService {

    List<TypeClasses> getAll();

    TypeClasses findById(Long id);

}
