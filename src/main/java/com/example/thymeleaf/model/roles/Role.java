package com.example.thymeleaf.model.roles;

public enum Role {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String code;

    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}