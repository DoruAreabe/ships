package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.repository.TeamsRepository;
import com.example.thymeleaf.service.FootballApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    @Autowired
    FootballApiService footballApiService;
    @Autowired
    TeamsRepository teamsRepository;


    @GetMapping("/teams")
    public String getTeams() {
        List<Team> teams = footballApiService.getTeamsByLeagueAndSeason(39, 2021);
        teamsRepository.saveAll(teams);
        return "index";
    }

}
