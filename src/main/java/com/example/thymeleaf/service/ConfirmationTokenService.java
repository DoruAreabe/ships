package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.ConfirmationToken;

public interface ConfirmationTokenService {
    void removeConfirmationTokenByUserId(long userId);

    void saveToken(ConfirmationToken token);

    ConfirmationToken getConfirmationTokenByToken(String confirmationToken);
}
