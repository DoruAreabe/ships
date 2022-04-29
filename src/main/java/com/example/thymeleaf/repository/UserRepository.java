package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("userRepository")
public interface UserRepository extends JpaRepository<User, String> {
    User findUserByEmailIdIgnoreCase(String emailId);
}
