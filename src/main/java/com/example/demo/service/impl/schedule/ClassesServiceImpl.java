package com.example.demo.service.impl.schedule;

import com.example.demo.model.schedule.Classes;
import com.example.demo.repository.schedule.*;
import com.example.demo.service.schedule.ClassesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ClassesServiceImpl implements ClassesService {

    private final ClassesRepository classesRepository;
    private final GroupsRepository groupsRepository;
    private final ProfessorRepository professorRepository;
    private final RoomsRepository roomsRepository;
    private final SubjectCourceRepository subjectCourceRepository;
    private final TypeClassesRepository typeClassesRepository;

    @Autowired
    public ClassesServiceImpl(ClassesRepository classesRepository,
                              GroupsRepository groupsRepository,
                              ProfessorRepository professorRepository,
                              RoomsRepository roomsRepository,
                              SubjectCourceRepository subjectCourceRepository,
                              TypeClassesRepository typeClassesRepository) {
        this.classesRepository = classesRepository;
        this.groupsRepository = groupsRepository;
        this.professorRepository = professorRepository;
        this.roomsRepository = roomsRepository;
        this.subjectCourceRepository = subjectCourceRepository;
        this.typeClassesRepository = typeClassesRepository;
    }

    @Override
    public List<Classes> getAll() {
        List<Classes> result = classesRepository.findAll();
        log.info("IN classes getAll - {}", result.size());
        return result;
    }

    @Override
    public Classes findById(Long id) {
        Classes result = classesRepository.findById(id).orElse(null);

        if (result == null) {
            log.info("IN classes findById: no classes found by id: {}", id);
            return null;
        }

        log.info("IN classes findById: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Classes create(Classes classes) {
        Classes nweClasses = classesRepository.save(classes);

        log.info("IN classes create: {}", nweClasses);

        return nweClasses;
    }

//    @Override
//    public Classes update(Long id, Long subjectId, Long typeId, Long professorID, Long roomID, Long assignedGroupID,
//                        String place, String week, Long positionInDay, String additionalRequirements) {
//
//        Classes classes = findById(id);
//
//        if(classes == null) { return classes; }
//
//        if(subjectId > 0 ) {
//            classes.setSubject(subjectCourceRepository.findById(subjectId).get());
//        }
//        if(typeId > 0) {
//            classes.setType(typeClassesRepository.findById(typeId).get());
//        }
//        if(professorID > 0) {
//            classes.setProfessor(professorRepository.findById(professorID).get());
//        }
//        if(roomID > 0) {
//            classes.setRoom(roomsRepository.findById(roomID).get());
//        }
//        if(assignedGroupID > 0) {
//            classes.setGroup(groupsRepository.findById(assignedGroupID).get());
//        }
//        if(place != null && !place.isEmpty()) {
//            classes.setPlace(Classes.Place.valueOf(place));
//        }
//        if(week != null && !week.isEmpty() && (Classes.Week.valueOf(week).equals(Classes.Week.FIRST) || Classes.Week.valueOf(week).equals(Classes.Week.SECOND))) {
//            classes.setWeek(Classes.Week.valueOf(week));
//        }
//        if(positionInDay != -1) {
//            classes.setPositionInDay(positionInDay);
//        }
//        if(additionalRequirements != null && !additionalRequirements.isEmpty()) {
//            classes.setAdditionalRequirements(additionalRequirements);
//        }
//
//        classesRepository.save(classes);
//
//        return classes;
//    }

    @Override
    public List<Classes> searchByCriteria(Long subjectId, Long typeId, Long professorId, Long roomId, Long groupId,
                                          String place, String week, Long positionInDay, String additionalRequirements) {

        List<Classes> classes = new ArrayList<>();

        Predicate<Classes> searchSubject = null;
        if (subjectId > 0) {
            searchSubject = m -> m.getSubject().getId().equals(subjectId);
        }

        Predicate<Classes> searchType = null;
        if (typeId > 0) {
            searchType = m -> m.getType().getId().equals(typeId);
        }

        Predicate<Classes> searchProfessorId = null;
        if (professorId > 0) {
            searchProfessorId = m -> m.getProfessor().getId().equals(professorId);
        }

        Predicate<Classes> searchRoomId = null;
        if (roomId > 0) {
            searchRoomId = m -> m.getRoom().getId().equals(roomId);
        }

        Predicate<Classes> searchGroupId = null;
        if (groupId > 0) {
            searchGroupId = m -> m.getGroup().getId().equals(groupId);
        }

        Predicate<Classes> searchPlace = null;
        if (place != null && !place.isEmpty()) {
            searchPlace = m -> m.getPlace() == Classes.Place.valueOf(place);
        }

        Predicate<Classes> searchWeek = null;
        if (week != null && !week.isEmpty()) {
            searchWeek = m -> m.getWeek().equals(Classes.Week.valueOf(week));
        }

        Predicate<Classes> searchIndexInDay = null;
        if (positionInDay > -1) {
            searchIndexInDay = m -> m.getPositionInDay().getId().equals(positionInDay);
        }

        Predicate<Classes> searchAdditionalRequirements = null;
        if (additionalRequirements != null && !additionalRequirements.isEmpty()) {
            searchAdditionalRequirements = m -> m.getAdditionalRequirements().equals(additionalRequirements);
        }

        ArrayList<Predicate<Classes>> arrayPredicateClasses = new ArrayList<>();
        arrayPredicateClasses.add(searchSubject);
        arrayPredicateClasses.add(searchType);
        arrayPredicateClasses.add(searchProfessorId);
        arrayPredicateClasses.add(searchRoomId);
        arrayPredicateClasses.add(searchGroupId);
        arrayPredicateClasses.add(searchPlace);
        arrayPredicateClasses.add(searchWeek);
        arrayPredicateClasses.add(searchIndexInDay);
        arrayPredicateClasses.add(searchAdditionalRequirements);

        arrayPredicateClasses.removeIf(Objects::isNull);

        Predicate<Classes> start = null;
        try {
            start = arrayPredicateClasses.get(0);
        } catch (IndexOutOfBoundsException ex) {
        }

        List<Classes> arrayListClasses = getAll();
        //getAllRooms().getTours(agensi.getName()).entrySet().stream().filter(x -> x.getValue() > 0).forEach(x -> arrayListTour.add(x.getKey()));

        for (Predicate<Classes> temp : arrayPredicateClasses) {
            start = start.and(temp);
        }

        log.info("IN classes searchByCriteria: {}", arrayListClasses);

        return (start != null) ? arrayListClasses.stream().filter(start).collect(Collectors.toList()) : arrayListClasses;

    }

    @Override
    public List<Classes> findByGroup(String group) {
        List<Classes> result = classesRepository.findByName(group);
        log.info("IN classes findByGroup: {}", result.size());
        return result;
    }

    @Override
    public List<Classes> orderByPlaceAndIndexInDay(String group) {
        List<Classes> result = classesRepository.orderByPlaceAndIndexInDay(group);
        log.info("IN classes orderByPlaceAndIndexInDay: {}", result.size());
        return result;
    }

}
