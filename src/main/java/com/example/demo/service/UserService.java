package com.example.demo.service;

import com.example.demo.dto.UserUpdateDto;
import com.example.demo.model.user.User;

import java.util.List;

public interface UserService {
    User create(User user);

    List<User> getAll();

    User findByUsername(String username);

    User findById(Long id);

    void delete(Long id);

    void update(UserUpdateDto userUpdateDto, Long id);
}
