package com.example.demo.service;

import com.example.demo.model.schedule.Room;

import java.util.List;

public interface RoomService {
    Room create(Room room);

    List<Room> getAll();

    Room findByName(String name);

    Room findById(Long id);

    void delete(Long id);

    List<Room> searchByCriteria(int korpus, String name, int countPlace, int countComputers, short stateRoom);
}
