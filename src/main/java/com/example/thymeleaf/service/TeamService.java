package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.Team;

import java.util.List;

public interface TeamService {
    List<Team> saveAll(List<Team> teams);
}
