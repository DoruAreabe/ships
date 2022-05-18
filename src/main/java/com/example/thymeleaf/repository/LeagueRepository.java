package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.League;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface LeagueRepository extends JpaRepository<League, Integer> {
    League findLeagueById(Integer id);

    List<League> findLeaguesByIsActive(Boolean isActive);
}
