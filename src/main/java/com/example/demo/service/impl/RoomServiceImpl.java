package com.example.demo.service.impl;

import com.example.demo.model.schedule.Room;
import com.example.demo.repository.RoomRepository;
import com.example.demo.service.RoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class RoomServiceImpl implements RoomService {

    private final RoomRepository roomRepository;

    public RoomServiceImpl(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @Override
    public Room create(Room room) {
        Room createdRoom = roomRepository.save(room);
        log.info("IN room create: {}", createdRoom);
        return createdRoom;
    }

    @Override
    public List<Room> getAll() {
        List<Room> result = roomRepository.findAll();
        log.info("IN room getAll: {}", result);
        return result;
    }

    @Override
    public Room findByName(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Room findById(Long id) {
        Room result = roomRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no room found by id: {}", id);
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
    public List<Room> searchByCriteria(int korpus, String name, int countPlace, int countComputers, short stateRoom) {

        List<Room> rooms = new ArrayList<>();

//        Predicate<Rooms> searchKorpus = null;
//        if(korpus != 0 ) { searchKorpus = m -> m.getKorpus() == korpus; }
        Predicate<Room> searchName = null;
        if (name != null && name.length() != 0) {
            searchName = m -> m.getName().equals(name);
        }
        Predicate<Room> searchCountPlace = null;
        if (countPlace != 0) {
            searchCountPlace = m -> m.getCountPlaces() == countPlace;
        }
        Predicate<Room> searchCountComputers = null;
        if (countComputers != 0) {
            searchCountComputers = m -> m.getCountPCs() == countComputers;
        }
        Predicate<Room> searchStateRoom = null;
        if (stateRoom != -1) {
            searchStateRoom = m -> m.getIsBlocked() == stateRoom;
        }

        ArrayList<Predicate<Room>> arrayPredicateRooms = new ArrayList<>();
        //arrayPredicateRooms.add(searchKorpus);
        arrayPredicateRooms.add(searchName);
        arrayPredicateRooms.add(searchCountPlace);
        arrayPredicateRooms.add(searchCountComputers);
        arrayPredicateRooms.add(searchStateRoom);

        arrayPredicateRooms.removeIf(item -> item == null);

        Predicate<Room> start = null;
        try {
            start = arrayPredicateRooms.get(0);
        } catch (IndexOutOfBoundsException ex) {
        }

        List<Room> arrayListRooms = new ArrayList<>();
        arrayListRooms = roomRepository.findAll();

        for (Predicate<Room> temp : arrayPredicateRooms) {
            start = start.and(temp);
        }

        return (start != null) ? arrayListRooms.stream().filter(start).collect(Collectors.toList()) : arrayListRooms;

    }

}
