package com.example.demo.rest.schedule;

import com.example.demo.model.schedule.*;
import com.example.demo.model.user.Role;
import com.example.demo.model.user.User;
import com.example.demo.service.RoomService;
import com.example.demo.service.UserService;
import com.example.demo.service.schedule.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/schedule/")
public class ScheduleRestControllerV1 {

    private final UserService userService;
    private final RoomService roomService;
    private final ClassesService classesService;
    private final GroupsService groupsService;
    private final ProfessorsService professorsService;

    private final TypeRoomService typeRoomService;
    private final TypeClassesService typeClassesService;
    private final LessonTimeService lessonTimeService;

    @Autowired
    public ScheduleRestControllerV1(RoomService roomService,
                                    ClassesService classesService,
                                    UserService userService,
                                    GroupsService groupsService,
                                    ProfessorsService professorsService,
                                    TypeRoomService typeRoomService,
                                    TypeClassesService typeClassesService,
                                    LessonTimeService lessonTimeService) {
        this.roomService = roomService;
        this.classesService = classesService;
        this.userService = userService;
        this.groupsService = groupsService;
        this.professorsService = professorsService;

        this.typeRoomService = typeRoomService;
        this.typeClassesService = typeClassesService;
        this.lessonTimeService = lessonTimeService;
    }

    @GetMapping(value = "classes/{id}")
    public ResponseEntity<Classes> getClassesById(@PathVariable(name = "id") Long id) {
        Classes classes = classesService.findById(id);

        if (classes == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(value = "classes/all")
    public ResponseEntity<List<Classes>> getAllClasses() {
        List<Classes> classes = classesService.getAll();

        if (classes == null && !classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(classes, HttpStatus.OK);

    }

    @GetMapping(value = "classes/criteria")
    public ResponseEntity<List<Classes>> searchClasses(
            @RequestParam(name = "subjectId", defaultValue = "0") Long subjectId,
            @RequestParam(name = "typeId", defaultValue = "0") Long typeId,
            @RequestParam(name = "professorId", defaultValue = "-1") Long professorId,
            @RequestParam(name = "roomId", defaultValue = "-1") Long roomId,
            @RequestParam(name = "groupId", defaultValue = "-1") Long groupId,
            @RequestParam(name = "place", defaultValue = "") String place,
            @RequestParam(name = "week", defaultValue = "") String week,
            @RequestParam(name = "positionInDay", defaultValue = "-1") Long positionInDay,
            @RequestParam(name = "additionalRequirements", defaultValue = "") String additionalRequirements) {

        List<Classes> classes = classesService.searchByCriteria(subjectId, typeId, professorId, roomId, groupId, place, week, positionInDay, additionalRequirements);

        if (classes == null && classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(value = "classes/user/all")
    public ResponseEntity<List<Classes>> getScheduleThisUser(Principal principal) {
        String username = principal.getName();

        User user = userService.findByUsername(username);

        boolean isTeacher = false;
        for (Role role : user.getRoles()) {
            if (role.getName().equals("ROLE_TEACHER")) {
                isTeacher = true;
                break;
            }
        }

        if (user.getStudent() == null && !isTeacher) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else if (user.getStudent() == null && isTeacher) {

            List<Classes> classes = professorsService.getProfessorClassesByUserId(user.getId());
            if (classes == null && classes.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(classes, HttpStatus.OK);

        }

        List<Classes> classes = classesService.orderByPlaceAndIndexInDay(user.getStudent().getGroup().getName());
        if (classes == null && !classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(classes, HttpStatus.OK);
    }

    @GetMapping(value = "classes/user/criteria")
    public ResponseEntity<List<Classes>> getScheduleThisUserByCriteria(Principal principal,
                                                                       @RequestParam(name = "subjectId", defaultValue = "0") Long subjectId,
                                                                       @RequestParam(name = "typeId", defaultValue = "0") Long typeId,
                                                                       @RequestParam(name = "professorId", defaultValue = "-1") Long professorId,
                                                                       @RequestParam(name = "roomId", defaultValue = "-1") Long roomId,
                                                                       @RequestParam(name = "groupId", defaultValue = "-1") Long groupId,
                                                                       @RequestParam(name = "place", defaultValue = "") String place,
                                                                       @RequestParam(name = "week", defaultValue = "") String week,
                                                                       @RequestParam(name = "positionInDay", defaultValue = "-1") Long positionInDay,
                                                                       @RequestParam(name = "additionalRequirements", defaultValue = "") String additionalRequirements) {

        User user = userService.findByUsername(principal.getName());

        if (user.getStudent().getGroup() == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        List<Classes> classes = classesService.searchByCriteria(subjectId, typeId, professorId, roomId, groupId, place, week, positionInDay, additionalRequirements);
        if (classes == null && !classes.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(classes, HttpStatus.OK);

    }

    @GetMapping(value = "typeRoom/{id}")
    public ResponseEntity<TypeRoom> getTypeRoomById(@PathVariable(name = "id") Long id) {
        TypeRoom typeRoom = typeRoomService.findById(id);

        if (typeRoom == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(typeRoom, HttpStatus.OK);
    }

    @GetMapping(value = "typeRoom/all")
    public ResponseEntity<List<TypeRoom>> getAllTypesRoom() {
        List<TypeRoom> typeRoom = typeRoomService.getAll();

        if (typeRoom == null && !typeRoom.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(typeRoom, HttpStatus.OK);

    }

    @GetMapping(value = "typeClasses/{id}")
    public ResponseEntity<TypeClasses> getTypeClassesById(@PathVariable(name = "id") Long id) {
        TypeClasses typeClasses = typeClassesService.findById(id);

        if (typeClasses == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(typeClasses, HttpStatus.OK);
    }

    @GetMapping(value = "typeClasses/all")
    public ResponseEntity<List<TypeClasses>> getAllTypesClasses() {
        List<TypeClasses> typeClasses = typeClassesService.getAll();

        if (typeClasses == null && !typeClasses.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(typeClasses, HttpStatus.OK);

    }

    @GetMapping(value = "lessonTime/{id}")
    public ResponseEntity<LessonTime> getLessonTimeById(@PathVariable(name = "id") Long id) {
        LessonTime lessonTime = lessonTimeService.findById(id);

        if (lessonTime == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lessonTime, HttpStatus.OK);
    }

    @GetMapping(value = "lessonTime/all")
    public ResponseEntity<List<LessonTime>> getAllLessonsTime() {
        List<LessonTime> lessonTime = lessonTimeService.getAll();

        if (lessonTime == null && !lessonTime.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(lessonTime, HttpStatus.OK);

    }

}
