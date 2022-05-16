package com.example.thymeleaf.model.responses.team;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TeamResponse {
    private String get;
    TeamRequestParameters parameters;
    List<Integer> errors = new ArrayList<>();
    private float results;
    Paging paging;
    List<ApiTeam> response = new ArrayList<>();
}
