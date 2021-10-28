package com.example.demo.service.impl;

import com.example.demo.model.user.Photo;
import com.example.demo.model.user.Student;
import com.example.demo.repository.StudentRepository;
import com.example.demo.service.StudentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentServiceImpl(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public Student findByUserId(Long id) {
        Student result = studentRepository.findByUserId(id);
        log.info("IN student findByUserId: {}", result);
        return result;
    }


}
