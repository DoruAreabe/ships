package com.example.thymeleaf.controller;

import com.example.thymeleaf.mappers.LeagueMapper;
import com.example.thymeleaf.mappers.UserMapper;
import com.example.thymeleaf.model.Options;
import com.example.thymeleaf.model.dto.LeagueDto;
import com.example.thymeleaf.model.dto.UserDto;
import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.service.FootballApiService;
import com.example.thymeleaf.service.LeagueService;
import com.example.thymeleaf.service.TeamService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
        List<League> allLeagues = leagueService.getAllLeagues();

        List<String> leaguesByName = allLeagues.stream()
                .map(x -> x.getName() + " (" + x.getCountry() + ") " + x.getId())
                .collect(Collectors.toList());

        Options options = new Options();
        options.setSelected(leaguesByName);

        model.addAttribute("options", options);
        return "adminPanel";
    }

    @PostMapping("/save-chosen-league")
    public String saveLeagues(@ModelAttribute("options") Options options, Model model) {
        for (int i = 0; i < options.getSelected().size(); i++) {
            String[] splitLeagueString = options.getSelected().get(i).split(" ");
            int leagueId = Integer.parseInt(splitLeagueString[splitLeagueString.length - 1]);

            LeagueDto leagueById = LeagueMapper.toLeagueDto(leagueService.findLeagueById((long) leagueId));
            if (Boolean.FALSE.equals(leagueById.getIsActive())) {
                List<Team> teamsByLeagueAndSeason = footballApiService.getTeamsByLeagueAndSeason(leagueId, SEASON);
                teamsService.saveAll(teamsByLeagueAndSeason);

                leagueById.setIsActive(true);
                leagueService.saveLeague(LeagueMapper.toEntity(leagueById));
            }
        }
        return showAvailableLeagues(model);
    }

    @GetMapping("/availableLeagues")
    public String showAvailableLeagues(Model model) {
        List<LeagueDto> leagueDtos = LeagueMapper.mapAllLeagues(leagueService.getAllActiveLeagues());
        model.addAttribute("leaguesDtos", leagueDtos);
        return "availableLeagues";
    }

}
