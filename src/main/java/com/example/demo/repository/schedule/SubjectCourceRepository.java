package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.SubjectCource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SubjectCourceRepository extends JpaRepository<SubjectCource, Long> {
    SubjectCource findById(long id);
}
