package com.example.demo.rest.consultation;

import com.example.demo.dto.ConsultationDto;
import com.example.demo.model.consultation.Consultation;
import com.example.demo.model.user.User;
import com.example.demo.rest.UserRestControllerV1;
import com.example.demo.service.AuthenticationFacadeService;
import com.example.demo.service.UserService;
import com.example.demo.service.consultation.ConsultationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(value = "/api/v1/consultations/")
public class ConsultationRestControllerV1 {

    private final ConsultationService consultationService;
    private final UserService userService;

    @Autowired
    public ConsultationRestControllerV1(ConsultationService consultationService, UserService userService) {
        this.consultationService = consultationService;
        this.userService = userService;
    }

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    private static final Logger log = LoggerFactory.getLogger(UserRestControllerV1.class);

    @Secured({"ROLE_TEACHER"})
    @PreAuthorize("hasRole('TEACHER')")
    @PostMapping("create")
    public ResponseEntity<Consultation> create(@RequestBody ConsultationDto consultationDto) throws Exception {
        log.info(String.format("received request to consultations create _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        Consultation newConsultation = consultationService.create(consultationDto);

        if (newConsultation == null) {
            throw new Exception("Сonsultation : " + consultationDto + " not added");
        }

        return new ResponseEntity<>(newConsultation, HttpStatus.OK);
    }

    //    @Secured({"ROLE_TEACHER"})
    @PreAuthorize("hasRole('TEACHER')")
    @GetMapping("my")
    public ResponseEntity<List<Consultation>> myConsultations(Principal principal) throws Exception {
        log.info(String.format("received request to consultations my _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());

        List<Consultation> consultations = consultationService.myConsultations(user.getUsername());

        if (consultations == null || consultations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("{id}/update")
    public ResponseEntity<Consultation> update(Principal principal, @PathVariable(name = "id") Long id, @RequestBody ConsultationDto consultationDto) throws Exception {
        log.info(String.format("received request to consultations id update _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());

        if (user.getCreated().contains(consultationService.findById(id))) {
            consultationService.update(consultationDto, id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @PreAuthorize("hasRole('TEACHER')")
    @PutMapping("{id}/delete")
    public ResponseEntity delete(Principal principal, @PathVariable(name = "id") Long id) throws Exception {
        log.info(String.format("received request to consultations id delete _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());

        if (user.getCreated().contains(consultationService.findById(id))) {
            consultationService.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }
    }

    @GetMapping(value = "{id}/statusConsultation")
    public ResponseEntity<Map<String, Boolean>> statusConsultation(Principal principal, @PathVariable(name = "id") Long id) {
        log.info(String.format("received request to consultations id status _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());
        Consultation consultation = consultationService.findById(id);

        if (consultation == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }

        Map<String, Boolean> statuses = new HashMap<>();

        if (user.getCreated().contains(consultation)) {
            statuses.put("isCanManage", true);

            return new ResponseEntity<>(statuses, HttpStatus.OK);
        }

        statuses.put("isCanManage", false);
        statuses.put("isSubscribed", isCanSubscribe(principal, id).getBody());

        return new ResponseEntity<>(statuses, HttpStatus.OK);
    }

    @GetMapping(value = "all")
    public ResponseEntity<List<Consultation>> getAllConsultations() {
        log.info(String.format("received request to consultations all _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        List<Consultation> consultations = consultationService.getAll();

        if (consultations == null || consultations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping(value = "mySubscriptions")
    public ResponseEntity<List<Consultation>> getSubscribeConsultations(Principal principal) {
        log.info(String.format("received request to consultations subscriptions _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());

        List<Consultation> consultations = consultationService.mySubscriptions(user.getUsername());

        if (consultations == null || consultations.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(consultations, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<Consultation> findById(@PathVariable(name = "id") Long id) {
        log.info(String.format("received request to consultations id _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        Consultation consultation = consultationService.findById(id);

        if (consultation == null) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(consultation, HttpStatus.OK);
    }

    @PutMapping(value = "{id}/unsubscribe")
    public ResponseEntity unsubscribe(Principal principal, @PathVariable(name = "id") Long idConsultation) {
        log.info(String.format("received request to consultations unsubscribe _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());
        Consultation consultation = consultationService.findById(idConsultation);

        if (user.getSubscribeOn().contains(consultation) && !user.getCreated().contains(consultation) && consultation != null) {
            consultationService.unsubscribe(idConsultation, user.getId());
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }

    @PostMapping(value = "{id}/subscribe")
    public ResponseEntity follow(Principal principal, @PathVariable(name = "id") Long idConsultation) {
        log.info(String.format("received request to consultations subscribe _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findByUsername(principal.getName());

        if (isCanSubscribe(principal, idConsultation).getBody()) {
            consultationService.subscribe(idConsultation, user.getId());
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

        if (isCanSubscribe(principal, idConsultation).getBody()) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.FORBIDDEN);
        }

    }

    //    @GetMapping(value = "{id}/isCanSubscribe")
    public ResponseEntity<Boolean> isCanSubscribe(Principal principal, @PathVariable(name = "id") Long id) {
        User user = userService.findByUsername(principal.getName());
        Consultation consultation = consultationService.findById(id);

        if (!user.getSubscribeOn().contains(consultation) && !user.getCreated().contains(consultation) && consultation != null) {
            return new ResponseEntity<>(true, HttpStatus.OK);
        }

        return new ResponseEntity<>(false, HttpStatus.OK);
    }


}
