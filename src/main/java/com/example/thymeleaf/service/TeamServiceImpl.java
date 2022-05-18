package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.model.entity.Team;
import com.example.thymeleaf.repository.TeamsRepository;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class TeamServiceImpl implements TeamService {

    private final TeamsRepository teamsRepository;

    public TeamServiceImpl(TeamsRepository teamsRepository) {
        this.teamsRepository = teamsRepository;
    }

    @Override
    public List<Team> saveAll(List<Team> teams) {
        return teamsRepository.saveAll(teams);
    }

    @Override
    @Transactional
    public void deleteTeamsByLeague(League league) {
        teamsRepository.deleteTeamByLeague(league);
    }
}
