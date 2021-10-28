package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.LessonTime;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LessonTimeRepository extends JpaRepository<LessonTime, Long> {
    LessonTime findById(long id);
}
