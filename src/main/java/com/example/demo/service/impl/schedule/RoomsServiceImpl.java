package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.Room;
import com.example.demo.repository.schedule.RoomsRepository;
import com.example.demo.service.schedule.RoomsService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class RoomsServiceImpl implements RoomsService {

    private final RoomsRepository roomsRepository;

    @Autowired
    public RoomsServiceImpl(RoomsRepository roomsRepository) {
        this.roomsRepository = roomsRepository;
    }

    @Override
    public List<Room> getAll() {
        List<Room> result = roomsRepository.findAll();
        log.info("IN room getAll: {}", result.size());
        return result;
    }

    @Override
    public Room findById(Long id) {
        Room result = roomsRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN room findById - no Rooms found by id: {}", id);
            return null;
        }

        log.info("IN room findById: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Room create(Room room) {
        Room newRoom = roomsRepository.save(room);

        log.info("IN room add: {}", newRoom);

        return newRoom;
    }


}
