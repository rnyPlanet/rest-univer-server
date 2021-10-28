package com.example.demo.service.schedule;

import com.example.demo.model.schedule.LessonTime;

import java.util.List;

public interface LessonTimeService {

    List<LessonTime> getAll();

    LessonTime findById(Long id);

}
