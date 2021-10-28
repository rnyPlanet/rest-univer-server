package com.example.demo.service;

import com.example.demo.model.user.Student;

public interface StudentService {

    Student findByUserId(Long id);

}
