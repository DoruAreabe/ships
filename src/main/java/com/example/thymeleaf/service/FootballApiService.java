package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.model.responses.TeamResponse;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.util.Collections;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class FootballApiService {

    HttpHeaders headers;
    String teamUri = "https://api-football-v1.p.rapidapi.com/v3/teams?league=idOfTheLeague&season=SeasonYear";

    public FootballApiService() {
        headers = new HttpHeaders();
        headers.put("x-rapidapi-host", Collections.singletonList("api-football-v1.p.rapidapi.com"));
        headers.put("x-rapidapi-key", Collections.singletonList("123"));
    }

    public List<Team> getTeamsByLeagueAndSeason(Integer league, Integer season) {
        RestTemplate restTemplate = new RestTemplate();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<>("", headers);
        ResponseEntity<TeamResponse> response = restTemplate.exchange(getTeamUri(league,season), HttpMethod.GET, httpEntity, TeamResponse.class);
        return response.getBody().getResponse().stream().map(x -> x.getTeam()).collect(Collectors.toList());
    }

    String getTeamUri(Integer league, Integer season) {
        return teamUri.replace("idOfTheLeague", String.valueOf(league)).replace("SeasonYear", String.valueOf(season));
    }

}
