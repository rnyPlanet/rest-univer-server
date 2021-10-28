package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.TypeClasses;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeClassesRepository extends JpaRepository<TypeClasses, Long> {
    TypeClasses findById(long id);
}
