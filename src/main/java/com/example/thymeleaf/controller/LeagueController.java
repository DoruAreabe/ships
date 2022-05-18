package com.example.thymeleaf.controller;

import com.example.thymeleaf.mappers.LeagueMapper;
import com.example.thymeleaf.model.Options;
import com.example.thymeleaf.model.dto.LeagueDto;
import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.service.FootballApiService;
import com.example.thymeleaf.service.LeagueService;
import com.example.thymeleaf.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/api/admin")
public class LeagueController {

    public static final int SEASON = 2021;

    private final LeagueService leagueService;
    private final FootballApiService footballApiService;
    private final TeamService teamsService;


    public LeagueController(LeagueService leagueService, FootballApiService footballApiService, TeamService teamsService) {
        this.leagueService = leagueService;
        this.footballApiService = footballApiService;
        this.teamsService = teamsService;
    }

    @GetMapping
    public String adminPanel(Model model) {
        List<String> leagues = getLeagues(LeagueMapper.mapAllEntity(leagueService.getAllLeagues()));

        List<String> activeLeagues = getLeagues(LeagueMapper.mapAllEntity(leagueService.getAllActiveLeagues()));

        Options options = new Options();
        options.setSelected(leagues);
        options.setActiveLeagues(activeLeagues);
        model.addAttribute("options", options);

        return "adminPanel";
    }

    private List<String> getLeagues(List<LeagueDto> leagueDtos) {
        return leagueDtos.stream()
                .map(x -> x.getName() + " (" + x.getCountry() + ") " + x.getId())
                .collect(Collectors.toList());
    }

    @PostMapping("/save-chosen-league")
    public String saveLeagues(@ModelAttribute("options") Options options, Model model) {
        List<League> allActiveLeagues = leagueService.getAllActiveLeagues();

        deleteTeamsFromActiveLeagues(allActiveLeagues);

        for (int i = 0; i < options.getActiveLeagues().size(); i++) {

            String[] splitLeagueString = options.getActiveLeagues().get(i).split(" ");
            int leagueId = Integer.parseInt(splitLeagueString[splitLeagueString.length - 1]);

            LeagueDto leagueById = LeagueMapper.toLeagueDto(leagueService.findLeagueById(leagueId));

            List<Team> teamsByLeagueAndSeason = footballApiService.getTeamsByLeagueAndSeason(LeagueMapper.toEntity(leagueById), SEASON);
            teamsService.saveAll(teamsByLeagueAndSeason);

            leagueById.setIsActive(true);
            leagueService.saveLeague(LeagueMapper.toEntity(leagueById));
        }
        return showAvailableLeagues(model);
    }

    private void deleteTeamsFromActiveLeagues(List<League> allActiveLeagues) {
        for (League league : allActiveLeagues) {
            teamsService.deleteTeamsByLeague(league);
            league.setIsActive(false);
            leagueService.saveLeague(league);
        }
    }

    @GetMapping("/availableLeagues")
    public String showAvailableLeagues(Model model) {
        List<LeagueDto> leagueDtos = LeagueMapper.mapAllEntity(leagueService.getAllActiveLeagues());
        model.addAttribute("leaguesDtos", leagueDtos);
        return "availableLeagues";
    }

    @GetMapping("/availableLeagues/{id}")
    public String deleteActiveLeagueById(@PathVariable Integer id) {
        League league = leagueService.getLeagueById(id);
        teamsService.deleteTeamsByLeague(league);
        league.setIsActive(false);
        leagueService.saveLeague(league);
        return "redirect:/api/admin/availableLeagues";
    }

    @GetMapping("/getData/leagues")
    public String downloadLeaguesFromApiFootball() {
        List<League> allLeagues = footballApiService.getAllLeagues();
        leagueService.saveAllLeagues(allLeagues);
        return "redirect:/api/admin";
    }
}
