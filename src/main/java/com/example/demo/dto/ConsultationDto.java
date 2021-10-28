package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ConsultationDto {
    private Long idCreatedUser;
    private Long idRoom;
    private Date dateOfPassage;
    private String description;

}
