package com.example.demo.rest.schedule;

import com.example.demo.model.schedule.Room;
import com.example.demo.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/rooms/")
public class RoomRestControllerV1 {

    private final RoomService roomService;

    @Autowired
    public RoomRestControllerV1(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Room>> getAll() {
        List<Room> result = roomService.getAll();

        if (result == null || result.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Room> getById(@PathVariable(name = "id") Long id) {
        Room result = roomService.findById(id);

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}