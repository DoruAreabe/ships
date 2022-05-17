package com.example.thymeleaf.model.responses.fixtures;

import com.example.thymeleaf.model.entity.League;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FixtureResponseEntity {
    FixtureEntity fixture;
    League league;
    Teams teams;
    Goals goals;
}
