package com.example.demo.dto;

import com.example.demo.model.consultation.Consultation;
import com.example.demo.model.schedule.Room;
import lombok.Data;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Data
public class UserCreatedConsultationDto {
    private long id;
    private Room room;
    private Date dateOfPassage;

    public static List<UserCreatedConsultationDto> fromConsultation(Collection<Consultation> consultation) {
        List<UserCreatedConsultationDto> listConsultation = new ArrayList();

        for (Consultation entity : consultation) {
            UserCreatedConsultationDto userCreatedConsultationDto = new UserCreatedConsultationDto();
            userCreatedConsultationDto.setId(entity.getId());
            userCreatedConsultationDto.setRoom(entity.getRoom());
            userCreatedConsultationDto.setDateOfPassage(entity.getDateOfPassage());
            listConsultation.add(userCreatedConsultationDto);
        }

        return listConsultation;
    }
}
