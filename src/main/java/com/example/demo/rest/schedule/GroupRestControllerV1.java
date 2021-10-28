package com.example.demo.rest.schedule;

import com.example.demo.model.schedule.Group;
import com.example.demo.service.schedule.GroupsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/groups/")
public class GroupRestControllerV1 {

    private final GroupsService groupsService;

    @Autowired
    public GroupRestControllerV1(GroupsService groupsService) {

        this.groupsService = groupsService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Group>> getAllGroups() {

        List<Group> result = groupsService.getAll();

        if (result == null || result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Group> getById(@PathVariable(name = "id") Long id) {

        Group result = groupsService.findById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
