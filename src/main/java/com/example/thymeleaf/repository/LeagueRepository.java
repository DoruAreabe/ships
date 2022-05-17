package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Long> {
    League findLeagueById(Long id);

    List<League> findLeaguesByIsActive(Boolean isActive);
}
