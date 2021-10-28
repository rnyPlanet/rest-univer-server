package com.example.demo.rest.teacher;

import com.example.demo.service.RoomService;
import com.example.demo.service.schedule.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole('ADMIN') or hasRole('SCHEDULER')")
@RequestMapping(value = "/api/v1/teacher/")
public class TeacherRestControllerV1 {

    private final RoomService roomService;
    private final ClassesService classesService;

    @Autowired
    public TeacherRestControllerV1(RoomService roomService, ClassesService classesService) {
        this.roomService = roomService;
        this.classesService = classesService;
    }


}
