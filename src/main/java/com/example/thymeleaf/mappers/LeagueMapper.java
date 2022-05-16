package com.example.thymeleaf.mappers;

import com.example.thymeleaf.model.League;
import com.example.thymeleaf.model.dto.LeagueDto;

public final class LeagueMapper {
    private LeagueMapper() {
    }
    public static League toEntity(LeagueDto dto) {
        League league = new League();
        league.setId(dto.getId());
        league.setName(dto.getName());
        league.setCountry(dto.getCountry());
        league.setCode(dto.getCode());
        league.setIsActive(dto.getIsActive());
        return league;
    }
    public static LeagueDto toLeagueDto(League league) {
        LeagueDto dto = new LeagueDto();
        dto.setId(league.getId());
        dto.setName(league.getName());
        dto.setCountry(league.getCountry());
        dto.setCode(league.getCode());
        dto.setIsActive(league.getIsActive());
        return dto;
    }



}
