package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.Classes;
import com.example.demo.model.schedule.Professor;
import com.example.demo.repository.schedule.ProfessorRepository;
import com.example.demo.service.schedule.ProfessorsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ProfessorsServiceImpl implements ProfessorsService {

    private final ProfessorRepository professorRepository;

    @Autowired
    public ProfessorsServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public List<Professor> getAll() {
        List<Professor> result = professorRepository.findAll();
        log.info("IN professor getAll: {}", result.size());
        return result;
    }

    @Override
    public Professor findById(Long id) {
        Professor result = professorRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN professor findById - no professor found by id: {}", id);
            return null;
        }

        log.info("IN professor findById: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Classes> getProfessorClassesById(Long id) {
        return professorRepository.getClassesByProfessorId(id);
    }

    @Override
    public List<Classes> getProfessorClassesByUserId(Long id) {
        return professorRepository.orderByPlaceAndPositionInDay(id);
    }

    @Override
    public Professor create(Professor professor) {
        Professor nweProfessor = professorRepository.save(professor);

        log.info("IN professor create: {}", nweProfessor);

        return nweProfessor;
    }

    @Override
    public Professor findByUserId(Long id) {
        Professor result = professorRepository.findByUserId(id);

        log.info("IN professor findByUserId: {}", result);
        return result;
    }


}
