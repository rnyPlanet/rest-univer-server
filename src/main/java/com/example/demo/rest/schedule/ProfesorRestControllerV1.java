package com.example.demo.rest.schedule;

import com.example.demo.model.schedule.Classes;
import com.example.demo.model.schedule.Professor;
import com.example.demo.service.schedule.ProfessorsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/professors/")
public class ProfesorRestControllerV1 {

    private final ProfessorsService professorsService;

    @Autowired
    public ProfesorRestControllerV1(ProfessorsService professorsService) {
        this.professorsService = professorsService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Professor>> getAllProfessors() {
        List<Professor> result = professorsService.getAll();

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Professor> getById(@PathVariable(name = "id") Long id) {
        Professor result = professorsService.findById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}