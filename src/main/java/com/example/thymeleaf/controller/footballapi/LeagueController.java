package com.example.thymeleaf.controller.footballapi;

import com.example.thymeleaf.model.League;
import com.example.thymeleaf.service.LeagueApiService;
import com.example.thymeleaf.service.LeagueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/leagues")
public class LeagueController {


    private final LeagueService leagueService;
    private final LeagueApiService leagueApiService;

    public LeagueController(LeagueService leagueService, LeagueApiService leagueApiService) {
        this.leagueService = leagueService;
        this.leagueApiService = leagueApiService;
    }

    @GetMapping("/football")
    public void downloadLeaguesFromApiFootball() {
        List<League> allLeagues = leagueApiService.getAllLeagues();
        leagueService.saveAllLeagues(allLeagues);
    }
}
