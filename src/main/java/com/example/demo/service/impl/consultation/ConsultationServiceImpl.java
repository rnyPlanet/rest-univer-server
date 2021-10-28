package com.example.demo.service.impl.consultation;

import com.example.demo.dto.ConsultationDto;
import com.example.demo.model.consultation.Consultation;
import com.example.demo.model.schedule.Room;
import com.example.demo.model.user.User;
import com.example.demo.repository.RoomRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.consultation.ConsultationsRepository;
import com.example.demo.service.consultation.ConsultationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class ConsultationServiceImpl implements ConsultationService {

    private final UserRepository userRepository;
    private final ConsultationsRepository consultationsRepository;
    private final RoomRepository roomsRepository;

    @Autowired
    public ConsultationServiceImpl(ConsultationsRepository consultationsRepository, UserRepository userRepository, RoomRepository roomRepository) {
        this.consultationsRepository = consultationsRepository;
        this.userRepository = userRepository;
        this.roomsRepository = roomRepository;
    }

    @Override
    public void delete(Long id) {
        consultationsRepository.deleteConsultationById(id);
        log.info("IN delete - consultation with id: {} successfully deleted", id);
    }

    @Override
    public Consultation create(ConsultationDto consultationDto) {

        Consultation newConsultation = new Consultation();

        User user = userRepository.findById(consultationDto.getIdCreatedUser()).get();
        Room room = roomsRepository.findById(consultationDto.getIdRoom()).get();

        newConsultation.setCreatedUser(user);
        newConsultation.setRoom(room);
        newConsultation.setDateOfPassage(consultationDto.getDateOfPassage());
        newConsultation.setDescription(consultationDto.getDescription());

        Consultation addedConsultation = consultationsRepository.save(newConsultation);

        log.info("IN consultation created - consultation: {} successfully created", addedConsultation);

        return addedConsultation;
    }

    @Override
    public List<Consultation> getAll() {
        List<Consultation> result = consultationsRepository.findAll();
        log.info("IN consultation getAll - {} consultations found", result.size());
        return result;
    }

    @Override
    public List<Consultation> myConsultations(String username) {
        List<Consultation> result = consultationsRepository.my(username);
        log.info("IN consultation myConsultations - {} consultations found", result.size());
        return result;
    }

    @Override
    public void update(ConsultationDto consultationDto, Long id) {
        consultationsRepository.update(id, consultationDto.getIdRoom(), consultationDto.getDateOfPassage(), consultationDto.getDescription());
        log.info("IN consultation update - {} successfully update", id);
    }

    @Override
    public List<Consultation> mySubscriptions(String username) {
        List<Consultation> result = consultationsRepository.mySubscriptions(username);
        log.info("IN consultation mySubscriptions - {} consultations found", result.size());
        return result;
    }

    @Override
    public Consultation findById(long id) {
        Consultation result = consultationsRepository.findById(id).orElse(null);

        if (result == null) {
            log.info("IN consultation findById - no consultation found by id: {}", id);
            return null;
        }

        log.info("IN consultation findById - consultation: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void unsubscribe(long idConsultation, long idUser) {
        consultationsRepository.unsubscribe(idConsultation, idUser);
        log.info("IN consultation unsubscribe - unsubscribe successfully by id: {}", idConsultation);
    }

    @Override
    public void subscribe(long idConsultation, long idUser) {
        consultationsRepository.subscribe(idConsultation, idUser);
        log.info("IN consultation subscribe - subscribe successfully by id: {}", idConsultation);
    }


}
