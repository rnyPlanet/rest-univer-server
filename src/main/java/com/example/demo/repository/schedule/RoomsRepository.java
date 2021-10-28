package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomsRepository extends JpaRepository<Room, Long> {
    Room findById(long id);
}
