package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeagueRepository extends JpaRepository<League, Long> {
    League findLeagueById(Long id);
}
