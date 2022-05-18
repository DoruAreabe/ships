package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.League;
import com.example.thymeleaf.repository.LeagueRepository;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Service
public class LeagueServiceImpl implements LeagueService {
    private final LeagueRepository repository;

    public LeagueServiceImpl(LeagueRepository repository) {
        this.repository = repository;
    }

    @Override
    public League findLeagueById(Integer id) {
        League league = repository.findLeagueById(id);
        if (league == null) {
            throw new EntityNotFoundException("League with id: " + id + " not found");
        }
        return league;
    }

    @Override
    public List<League> getAllLeagues() {
        List<League> leagues = repository.findAll();
        if (leagues.isEmpty()) {
            throw new EntityNotFoundException("Leagues table is empty");
        }
        return leagues;
    }

    @Override
    public void saveAllLeagues(List<League> leagues) {
        if (leagues == null) {
            throw new RuntimeException("League is null");
        }
        if (leagues.isEmpty()) {
            throw new RuntimeException("League is empty");
        }
        repository.saveAll(leagues);
    }

    @Override
    public void saveLeague(League league) {
        repository.save(league);
    }

    @Override
    public List<League> getAllActiveLeagues() {
        return repository.findLeaguesByIsActive(true);
    }

    @Override
    public League getLeagueById(Integer id) {
        return repository.findLeagueById(id);
    }

}
