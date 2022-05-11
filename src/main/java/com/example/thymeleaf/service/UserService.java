package com.example.thymeleaf.service;

import com.example.thymeleaf.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    List<User> getAllUsers();

    void deleteUserByEmailId(String emailId);

    void saveUser(User user);

    User findUserByEmailIdIgnoreCase(String emailId);

    void processOAuthPostLogin(CustomOAuth2User oAuth2User);

}
