package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.repository.TeamsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    TeamsRepository teamsRepository;

    private final TeamsRepository teamsRepository;

    public TeamServiceImpl(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    @Override
    public List<Team> saveAll(List<Team> teams) {
        return teamsRepository.saveAll(teams);
    }
}
