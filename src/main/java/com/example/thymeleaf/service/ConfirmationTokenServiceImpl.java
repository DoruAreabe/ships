package com.example.thymeleaf.service;

import com.example.thymeleaf.model.ConfirmationToken;
import com.example.thymeleaf.repository.ConfirmationTokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfirmationTokenServiceImpl implements ConfirmationTokenService {

    @Autowired
    ConfirmationTokenRepository repository;

    @Override
    public void removeConfirmationTokenByUserId(long userId) {
        repository.removeConfirmationTokenByUser_Userid(userId);
    }

    @Override
    public void saveToken(ConfirmationToken token) {
        repository.save(token);
    }

    @Override
    public ConfirmationToken getConfirmationTokenByToken(String confirmationToken) {
        return repository.findByConfirmationTokenName(confirmationToken);
    }
}
