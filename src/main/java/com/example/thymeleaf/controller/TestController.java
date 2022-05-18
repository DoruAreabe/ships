package com.example.thymeleaf.controller;

import com.example.thymeleaf.model.entity.Fixture;
import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.service.FixtureService;
import com.example.thymeleaf.service.FootballApiService;
import com.example.thymeleaf.service.LeagueService;
import com.example.thymeleaf.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TestController {

    private final FootballApiService footballApiService;
    private final TeamService teamsService;
    private final LeagueService leagueService;
    private final FixtureService fixtureService;

    public TestController(FootballApiService footballApiService, TeamService teamsService, LeagueService leagueService, FixtureService fixtureService) {
        this.footballApiService = footballApiService;
        this.teamsService = teamsService;
        this.leagueService = leagueService;
        this.fixtureService = fixtureService;
    }


    @GetMapping("/teams")
    public String getTeams() {
        League league = leagueService.findLeagueById(39);
        List<Team> teams = footballApiService.getTeamsByLeagueAndSeason(league, 2021);
        teamsService.saveAll(teams);
        return "index";
    }

    @GetMapping("/leagues")
    public String downloadLeaguesFromApiFootball() {
        List<League> allLeagues = footballApiService.getAllLeagues();
        leagueService.saveAllLeagues(allLeagues);
        return "index";
    }

    @GetMapping("/fixturesBetween")
    public String getFixturesBetween() {
        List<Fixture> fixtures = footballApiService.getFixturesBetween(39, 2021, "2022-05-17", "2022-05-19");
        fixtureService.saveAll(fixtures);
        return "index";
    }
}

