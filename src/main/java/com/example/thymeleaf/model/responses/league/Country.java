package com.example.thymeleaf.controller.footballapi.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Country {
    private String name;
    private String code;
    private String flag;
}
