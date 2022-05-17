package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.Fixture;

import java.util.List;

public interface FixtureService {
    List<Fixture> saveAll(List<Fixture> items);
}
