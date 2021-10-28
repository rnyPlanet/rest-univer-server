package com.example.demo.service.impl;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.Status;
import com.example.demo.model.user.Role;
import com.example.demo.model.user.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.consultation.ConsultationsRepository;
import com.example.demo.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final ConsultationsRepository consultationsRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository,
                           RoleRepository roleRepository,
                           BCryptPasswordEncoder passwordEncoder,
                           ConsultationsRepository consultationsRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.consultationsRepository = consultationsRepository;
    }

    @Override
    public User create(User user) {
        Role roleUser = roleRepository.findByName("ROLE_USER");
        List<Role> userRoles = new ArrayList<>();
        userRoles.add(roleUser);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(userRoles);
        user.setStatus(Status.NOT_ACTIVE);

        try {
            User registeredUser = userRepository.save(user);
            log.info("IN user register: {}", registeredUser);
            return registeredUser;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public List<User> getAll() {
        List<User> result = userRepository.findAll();
        log.info("IN user getAll: {}", result.size());
        return result;
    }

    @Override
    public User findByUsername(String username) {
        User result = userRepository.findByUsername(username);
        log.info("IN user findByUsername: {} found by username: {}", result, username);
        return result;
    }

    @Override
    public User findById(Long id) {
        User result = userRepository.findById(id).orElse(null);

        if (result == null) {
            log.warn("IN findById - no user found by id: {}", id);
            return null;
        }

        log.info("IN user findById: {} found by id: {}", result, id);
        return result;
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
        log.info("IN user delete by id: {}", id);
    }

    @Override
    public void update(UserUpdateDto userUpdateDto, Long id) {
        if (userUpdateDto.getPassword() != null && !userUpdateDto.getPassword().isEmpty()) {
            userRepository.updateAcc(
                    userUpdateDto.getPatronymic(),
                    userUpdateDto.getFirstName(),
                    userUpdateDto.getLastName(),
                    userUpdateDto.getLastNameInternational(),
                    userUpdateDto.getFirstNameInternational(),
                    userUpdateDto.getPatronymicNameInternational(),
                    userUpdateDto.getEmail(),
                    userUpdateDto.getPassword(),
                    (userUpdateDto.getPhoto() != null) ? userUpdateDto.getPhoto().getId() : null,
                    userUpdateDto.getTelefon1(),
                    userUpdateDto.getTelefon2(),
                    id
            );
        } else {
            userRepository.updateAcc(
                    userUpdateDto.getPatronymic(),
                    userUpdateDto.getFirstName(),
                    userUpdateDto.getLastName(),
                    userUpdateDto.getLastNameInternational(),
                    userUpdateDto.getFirstNameInternational(),
                    userUpdateDto.getPatronymicNameInternational(),
                    userUpdateDto.getEmail(),
                    (userUpdateDto.getPhoto() != null) ? userUpdateDto.getPhoto().getId() : null,
                    userUpdateDto.getTelefon1(),
                    userUpdateDto.getTelefon2(),
                    id
            );
        }

    }

}
