package com.example.thymeleaf.service;

import com.example.thymeleaf.controller.footballapi.response.ApiLeague;
import com.example.thymeleaf.controller.footballapi.response.Country;
import com.example.thymeleaf.controller.footballapi.response.LeagueResponse;
import com.example.thymeleaf.controller.footballapi.response.Response;
import com.example.thymeleaf.model.League;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LeagueApiService {

    private static final String API_FOOTBALL_LEAGUES_URI = "https://api-football-v1.p.rapidapi.com/v3/leagues";

    public List<League> getAllLeagues() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        headers.put("x-rapidapi-host", Collections.singletonList("api-football-v1.p.rapidapi.com"));
        headers.put("x-rapidapi-key", Collections.singletonList("4b48bcc0c9mshf3d7f63ab7207bfp19253fjsn3c9e50173115"));

        HttpEntity<String> entity = new HttpEntity<>("", headers);

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Response> response = restTemplate.exchange(API_FOOTBALL_LEAGUES_URI, HttpMethod.GET, entity, Response.class);

        List<LeagueResponse> collectLeagues = response.getBody().getResponse().stream()
                .map(ApiLeague::getLeague)
                .collect(Collectors.toList());
        List<Country> countries = response.getBody().getResponse().stream().map(ApiLeague::getCountry).collect(Collectors.toList());

        List<League> leagues = new ArrayList<>();

        createLeagueEntity(collectLeagues, countries, leagues);
        return leagues;
    }

    private void createLeagueEntity(List<LeagueResponse> collectLeagues, List<Country> countries, List<League> leagues) {
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
