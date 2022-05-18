package com.example.thymeleaf.repository;

import com.example.thymeleaf.model.entity.Fixture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FixtureRepository extends JpaRepository<Fixture, Integer> {
}
