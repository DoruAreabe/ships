package com.example.thymeleaf.service;

import com.example.thymeleaf.model.responses.league.ApiLeague;
import com.example.thymeleaf.model.responses.league.Country;
import com.example.thymeleaf.model.responses.league.LeagueDetails;
import com.example.thymeleaf.model.responses.league.LeaguesResponse;
import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.model.responses.team.TeamResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FootballApiService {

    HttpHeaders headers;
    HttpEntity<String> httpEntity;
    RestTemplate restTemplate;
    private static final String API_FOOTBALL_TEAM_URI = "https://api-football-v1.p.rapidapi.com/v3/teams?league=idOfTheLeague&season=SeasonYear";
    private static final String API_FOOTBALL_LEAGUES_URI = "https://api-football-v1.p.rapidapi.com/v3/leagues";


    public FootballApiService() {
        headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.put("x-rapidapi-host", Collections.singletonList("api-football-v1.p.rapidapi.com"));
        headers.put("x-rapidapi-key", Collections.singletonList("4b48bcc0c9mshf3d7f63ab7207bfp19253fjsn3c9e50173115"));
        this.httpEntity = new HttpEntity<>("", headers);
    }

    public List<Team> getTeamsByLeagueAndSeason(Integer league, Integer season) {
        restTemplate=new RestTemplate();
        ResponseEntity<TeamResponse> response = restTemplate.exchange(getTeamUri(league, season), HttpMethod.GET, httpEntity, TeamResponse.class);
        return response.getBody().getResponse().stream()
                .map(x -> x.getTeam()).collect(Collectors.toList());

    }

    String getTeamUri(Integer league, Integer season) {
        return API_FOOTBALL_TEAM_URI.replace("idOfTheLeague", String.valueOf(league)).replace("SeasonYear", String.valueOf(season));
    }


    public List<League> getAllLeagues() {
        restTemplate=new RestTemplate();
        ResponseEntity<LeaguesResponse> response = restTemplate.exchange(API_FOOTBALL_LEAGUES_URI, HttpMethod.GET, httpEntity, LeaguesResponse.class);

        List<LeagueDetails> collectLeagues = response.getBody().getResponse().stream()
                .map(ApiLeague::getLeague)
                .collect(Collectors.toList());
        List<Country> countries = response.getBody().getResponse().stream().map(ApiLeague::getCountry).collect(Collectors.toList());

        List<League> leagues = new ArrayList<>();

        createLeagueEntity(collectLeagues, countries, leagues);
        return leagues;
    }

    private void createLeagueEntity(List<LeagueDetails> collectLeagues, List<Country> countries, List<League> leagues) {
        for (int i = 0; i < collectLeagues.size(); i++) {
            League leagueEntity = new League();

            long id = collectLeagues.get(i).getId();
            String name = collectLeagues.get(i).getName();

            String countryName = countries.get(i).getName();
            String countryCode = countries.get(i).getCode();

            leagueEntity.setId(id);
            leagueEntity.setName(name);
            leagueEntity.setCountry(countryName);
            leagueEntity.setCode(countryCode);
            leagueEntity.setIsActive(false);
            leagues.add(leagueEntity);
        }
    }

}
