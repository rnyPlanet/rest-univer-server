package com.example.demo.rest;

import com.example.demo.model.user.User;
import com.example.demo.service.AuthenticationFacadeService;
import com.example.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/v1/admin/")
public class AdminRestControllerV1 {

    private final UserService userService;

    @Autowired
    public AdminRestControllerV1(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Autowired
    private AuthenticationFacadeService authenticationFacadeService;

    private static final Logger log = LoggerFactory.getLogger(AdminRestControllerV1.class);

    @GetMapping(value = "users/{id}")
    public ResponseEntity<User> getUserById(@PathVariable(name = "id") Long id) {
        log.info(String.format("received request to admin users id _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        User user = userService.findById(id);

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping(value = "users/all")
    public ResponseEntity<List<User>> getUsers() {
        log.info(String.format("received request to admin users all _ by %s", authenticationFacadeService.getAuthentication().getPrincipal()));

        List<User> users = userService.getAll();

        if (users == null || users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }

        return new ResponseEntity<>(users, HttpStatus.OK);
    }
}
