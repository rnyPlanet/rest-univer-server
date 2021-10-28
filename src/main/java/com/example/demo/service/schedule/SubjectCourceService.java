package com.example.demo.service.schedule;

import com.example.demo.model.schedule.SubjectCource;

import java.util.List;

public interface SubjectCourceService {

    List<SubjectCource> getAll();

    SubjectCource findById(Long id);

}
