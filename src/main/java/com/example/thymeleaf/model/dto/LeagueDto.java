package com.example.thymeleaf.model.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDto {

    private Integer id;
    private String name;
    private String code;
    private String country;
    private Boolean isActive;
}
