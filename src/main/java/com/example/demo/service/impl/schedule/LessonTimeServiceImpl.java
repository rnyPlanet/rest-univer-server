package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.LessonTime;
import com.example.demo.repository.schedule.LessonTimeRepository;
import com.example.demo.service.schedule.LessonTimeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class LessonTimeServiceImpl implements LessonTimeService {

    private final LessonTimeRepository lessonTimeRepository;

    @Autowired
    public LessonTimeServiceImpl(LessonTimeRepository lessonTimeRepository) {
        this.lessonTimeRepository = lessonTimeRepository;
    }

    @Override
    public List<LessonTime> getAll() {
        List<LessonTime> result = lessonTimeRepository.findAll();
        log.info("IN lessonTime getAll: {}", result.size());
        return result;
    }

    @Override
    public LessonTime findById(Long id) {
        LessonTime result = lessonTimeRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN lessonTime findById - no lessonTime found by id: {}", id);
            return null;
        }

        log.info("IN lessonTime findById: {} found by id: {}", result, id);
        return result;
    }

}
