package com.example.thymeleaf.model.responses.league;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeagueDetails {
    private Integer id;
    private String name;
    private String type;
    private String logo;
}
