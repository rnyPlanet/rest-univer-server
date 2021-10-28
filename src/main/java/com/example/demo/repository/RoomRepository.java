package com.example.demo.repository;

import com.example.demo.model.schedule.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Long> {
    Room findByName(String name);

    List<Room> findAll();
}
