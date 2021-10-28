package com.example.demo.service.consultation;

import com.example.demo.dto.ConsultationDto;
import com.example.demo.model.consultation.Consultation;

import java.util.List;

public interface ConsultationService {
    Consultation create(ConsultationDto consultationDto);

    List<Consultation> getAll();

    void delete(Long id);

    List<Consultation> myConsultations(String username);

    List<Consultation> mySubscriptions(String username);

    void update(ConsultationDto consultationDto, Long id);

    Consultation findById(long id);

    void unsubscribe(long idConsultation, long idUser);

    void subscribe(long idConsultation, long idUser);
}
