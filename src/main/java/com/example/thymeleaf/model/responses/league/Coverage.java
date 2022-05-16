package com.example.thymeleaf.controller.footballapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Coverage {
    private Fixtures FixturesObject;
    private boolean standings;
    private boolean players;
    private boolean top_scorers;
    private boolean top_assists;
    private boolean top_cards;
    private boolean injuries;
    private boolean predictions;
    private boolean odds;
}
