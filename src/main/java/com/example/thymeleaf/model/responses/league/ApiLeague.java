package com.example.thymeleaf.model.responses.league;

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
    private LeagueDetails league;
    private Country country;
    private List<Season> seasons;
}
