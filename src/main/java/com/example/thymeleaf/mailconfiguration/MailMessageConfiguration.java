package com.example.thymeleaf.mailconfiguration;

import org.springframework.mail.SimpleMailMessage;

public class MailMessageConfiguration {
    private static final String SENDER = "ship.project.gft@gmail.com";
    private static final String SUBJECT = "Complete Registration!";
    private static final String URL_CONFIRMATION = "http://localhost:8080/registration/confirm-account?token=";
    private static final String CONFIRMATION_MESSAGE = "To confirm your account, please click here : ";

    private final SimpleMailMessage email;

    public MailMessageConfiguration(String email, String token) {
        this.email = new SimpleMailMessage();
        setEmailMessage(email, token);
    }

    private void setEmailMessage(String email, String token) {
        this.email.setTo(email);
        this.email.setSubject(SUBJECT);
        this.email.setFrom(SENDER);
        this.email.setText(CONFIRMATION_MESSAGE + URL_CONFIRMATION + token);
    }

    public SimpleMailMessage getEmail() {
        return email;
    }
}
