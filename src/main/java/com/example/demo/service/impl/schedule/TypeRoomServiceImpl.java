package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.TypeRoom;
import com.example.demo.repository.schedule.TypeRoomRepository;
import com.example.demo.service.schedule.TypeRoomService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class TypeRoomServiceImpl implements TypeRoomService {

    private final TypeRoomRepository typeRoomRepository;

    @Autowired
    public TypeRoomServiceImpl(TypeRoomRepository typeRoomRepository) {
        this.typeRoomRepository = typeRoomRepository;
    }

    @Override
    public List<TypeRoom> getAll() {
        List<TypeRoom> result = typeRoomRepository.findAll();
        log.info("IN typeRoom getAll: {}", result.size());
        return result;
    }

    @Override
    public TypeRoom findById(Long id) {
        TypeRoom result = typeRoomRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN typeRoom findById - no type room found by id: {}", id);
            return null;
        }

        log.info("IN typeRoom findById: {} found by id: {}", result, id);
        return result;
    }

}
