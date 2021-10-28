package com.example.demo.service.schedule;

import com.example.demo.model.schedule.Group;

import java.util.List;

public interface GroupsService {

    Group create(Group group);

    List<Group> getAll();

    Group findById(Long id);

    void delete(Long id);

}
