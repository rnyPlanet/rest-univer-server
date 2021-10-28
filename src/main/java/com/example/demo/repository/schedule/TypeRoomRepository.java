package com.example.demo.repository.schedule;

import com.example.demo.model.schedule.TypeRoom;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TypeRoomRepository extends JpaRepository<TypeRoom, Long> {
    TypeRoom findById(long id);
}
