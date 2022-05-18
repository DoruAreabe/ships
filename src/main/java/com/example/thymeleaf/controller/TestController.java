package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.service.FootballApiService;
import com.example.thymeleaf.service.LeagueService;
import com.example.thymeleaf.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("getData")
public class TestController {

    private final FootballApiService footballApiService;
    private final TeamService teamsService;
    private final LeagueService leagueService;

    public TestController(FootballApiService footballApiService, TeamService teamsService, LeagueService leagueService) {
        this.footballApiService = footballApiService;
        this.teamsService = teamsService;
        this.leagueService = leagueService;
    }


    @GetMapping("/teams")
    public String getTeams() {
//        List<Team> teams = footballApiService.getTeamsByLeagueAndSeason(39, 2021);
//        teamsService.saveAll(teams);
        return "index";
    }

    @GetMapping("/leagues")
    public String downloadLeaguesFromApiFootball() {
        List<League> allLeagues = footballApiService.getAllLeagues();
        leagueService.saveAllLeagues(allLeagues);
        return "index";
    }
}

