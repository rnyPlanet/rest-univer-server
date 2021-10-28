/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example.demo.rest.schedule;

import com.example.demo.service.RoomService;
import com.example.demo.service.schedule.ClassesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@PreAuthorize("hasRole('ADMIN') or hasRole('SCHEDULER')")
@RequestMapping(value = "/api/v1/scheduler/")
public class SchedulerRestControllerV1 {

    private final RoomService roomService;
    private final ClassesService classesService;

    @Autowired
    public SchedulerRestControllerV1(RoomService roomService, ClassesService classesService) {
        this.roomService = roomService;
        this.classesService = classesService;
    }

//    @PutMapping(value = "classes/update/{id}")
//    public ResponseEntity<Classes> updateClasses(@PathVariable(name = "id") Long id,
//                                @RequestParam(name = "subjectId", defaultValue="0") Long subjectId,
//                                @RequestParam(name = "type", defaultValue="") String type,
//                                @RequestParam(name = "professorID", defaultValue="0") Long professorID,
//                                @RequestParam(name = "roomID", defaultValue="0") Long roomID,
//                                @RequestParam(name = "assignedGroupID", defaultValue="0") Long assignedGroupID,
//                                @RequestParam(name = "place", defaultValue="") String place,
//                                @RequestParam(name = "week", defaultValue="") String week,
//                                @RequestParam(name = "indexInDay", defaultValue="-1") Long positionInDay,
//                                @RequestParam(name = "additionalRequirements", defaultValue="") String additionalRequirements) {
//
//        Classes classes = classesService.update(id, subjectId, type, professorID, roomID, assignedGroupID, place, week, positionInDay, additionalRequirements);
//
//        if (classes == null ) {
//            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
//        }
//
//        return new ResponseEntity<>(classes, HttpStatus.OK);
//    }

}
