package com.example.demo.service.schedule;

import com.example.demo.model.schedule.Classes;

import java.util.List;

public interface ClassesService {

    Classes create(Classes classes);

    List<Classes> getAll();

    Classes findById(Long id);

    void delete(Long id);

//    Classes update(Long id, Long subjectId, Long typeId, Long professorID, Long roomID, Long assignedGroupID,
//                   String place, String week, Long positionInDay, String additionalRequirements);

    List<Classes> searchByCriteria(Long subjectId, Long typeId, Long professorId, Long roomId, Long groupId,
                                   String place, String week, Long positionInDay, String additionalRequirements);

    List<Classes> findByGroup(String group);

    List<Classes> orderByPlaceAndIndexInDay(String group);
}
