package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamsRepository extends JpaRepository<Team, Integer> {
}
