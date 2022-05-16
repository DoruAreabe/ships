package com.example.thymeleaf.controller.footballapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ApiLeague {
    private LeagueResponse league;
    private Country country;
    private List<Season> seasons;
}
