package com.example.thymeleaf.model.responses.fixtures;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FixtureParameters {
    private String league;
    private String from;
    private String to;
    private String season;
}
