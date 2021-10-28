package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.Group;
import com.example.demo.repository.schedule.GroupsRepository;
import com.example.demo.service.schedule.GroupsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class GroupsServiceImpl implements GroupsService {

    private final GroupsRepository groupsRepository;

    @Autowired
    public GroupsServiceImpl(GroupsRepository groupsRepository) {
        this.groupsRepository = groupsRepository;
    }

    @Override
    public List<Group> getAll() {
        List<Group> result = groupsRepository.findAll();
        log.info("IN group getAll: {}", result.size());
        return result;
    }

    @Override
    public Group findById(Long id) {
        Group result = groupsRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN group findById - no groups found by id: {}", id);
            return null;
        }

        log.info("IN group findById: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Group create(Group group) {
        Group nweGroup = groupsRepository.save(group);

        log.info("IN group add: {}", nweGroup);

        return nweGroup;
    }


}
