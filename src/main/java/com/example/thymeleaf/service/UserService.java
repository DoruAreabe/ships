package com.example.thymeleaf.service;

import com.example.thymeleaf.model.User;
import com.example.thymeleaf.controller.dto.UserRegistrationDto;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void saveUserRegistrationDto(UserRegistrationDto userRegistrationDto);

    void deleteUserByUsername(String username);

    User getUserByUsername(String username);

    void saveUser(User user);
}
