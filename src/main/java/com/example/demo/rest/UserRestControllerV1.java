package com.example.demo.rest;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.schedule.Professor;
import com.example.demo.model.user.Student;
import com.example.demo.model.user.User;
import com.example.demo.service.AuthenticationFacadeService;
import com.example.demo.service.StudentService;
import com.example.demo.service.UserService;
import com.example.demo.service.schedule.ProfessorsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping(value = "/api/v1/users/")
public class UserRestControllerV1 {

    private final UserService userService;
    private final StudentService studentService;
    private final ProfessorsService professorsService;

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    private static final Logger log = LoggerFactory.getLogger(UserRestControllerV1.class);

    @Autowired
    public UserRestControllerV1(UserService userService,
                                StudentService studentService,
                                ProfessorsService professorsService) {
        this.userService = userService;
        this.studentService = studentService;
        this.professorsService = professorsService;
    }

    @GetMapping(value = "my_account")
    public ResponseEntity<User> getThisUser(Principal principal) {
        log.info(String.format("received request to users my account _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User result = userService.findByUsername(principal.getName());

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @GetMapping(value = "my_account/student")
    public ResponseEntity<Student> getThisUserLikeStudent(Principal principal) {
        log.info(String.format("received request to users my account student _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User result = userService.findByUsername(principal.getName());

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Student student = studentService.findByUserId(result.getId());
        if (student == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @GetMapping(value = "my_account/professor")
    public ResponseEntity<Professor> getThisUserLikeProfessor(Principal principal) {
        log.info(String.format("received request to users my account professor _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User result = userService.findByUsername(principal.getName());

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        Professor professor = professorsService.findByUserId(result.getId());
        if (professor == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(professor, HttpStatus.OK);
    }

    @PutMapping(value = "my_account/update")
    public ResponseEntity<User> update(@RequestBody UserUpdateDto userUpdateDto, Principal principal) {
        log.info(String.format("received request to users my account update _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User result = userService.findByUsername(principal.getName());

        if (result == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        userService.update(userUpdateDto, result.getId());

        result = getThisUser(principal).getBody();

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}