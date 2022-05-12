package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.ConfirmationToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConfirmationTokenRepository extends JpaRepository<ConfirmationToken, Long> {
    ConfirmationToken findByConfirmationTokenName(String confirmationTokenName);

    void removeConfirmationTokenByUser_Userid(long userId);
}
