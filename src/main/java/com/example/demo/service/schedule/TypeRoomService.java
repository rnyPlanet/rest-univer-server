package com.example.demo.service.schedule;

import com.example.demo.model.schedule.TypeRoom;

import java.util.List;

public interface TypeRoomService {

    List<TypeRoom> getAll();

    TypeRoom findById(Long id);

}
