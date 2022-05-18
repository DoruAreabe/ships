package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {
    @Autowired
    TeamsRepository teamsRepository;

    @Override
    public List<Team> saveAll(List<Team> teams) {
        return teamsRepository.saveAll(teams);
    }
}
