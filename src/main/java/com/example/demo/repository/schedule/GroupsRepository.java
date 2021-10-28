package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.Group;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupsRepository extends JpaRepository<Group, Long> {
    Group findById(long id);

}
