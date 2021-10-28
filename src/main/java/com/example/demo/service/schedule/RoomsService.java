package com.example.demo.service.schedule;

import com.example.demo.model.schedule.Room;

import java.util.List;

public interface RoomsService {

    Room create(Room room);

    List<Room> getAll();

    Room findById(Long id);

    void delete(Long id);

}
