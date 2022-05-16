package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailIdIgnoreCase(String emailId);
}
