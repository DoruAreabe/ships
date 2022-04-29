package com.example.thymeleaf.service;

import com.example.thymeleaf.model.ConfirmationToken;

public interface ConfirmationTokenService {
    void removeConfirmationTokenByUserId(long userId);

    void saveToken(ConfirmationToken token);

    ConfirmationToken getConfirmationTokenByToken(String confirmationToken);
}
