package com.example.demo.service.schedule;

import com.example.demo.model.schedule.Classes;
import com.example.demo.model.schedule.Professor;

import java.util.List;

public interface ProfessorsService {

    Professor create(Professor professor);

    List<Professor> getAll();

    Professor findById(Long id);

    void delete(Long id);

    List<Classes> getProfessorClassesById(Long id);

    List<Classes> getProfessorClassesByUserId(Long id);

    Professor findByUserId(Long id);

}
