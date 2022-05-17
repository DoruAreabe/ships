package com.example.thymeleaf.service;

import com.example.thymeleaf.model.entity.Fixture;
import com.example.thymeleaf.repository.FixtureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class FixtureServiceImpl implements FixtureService {
    @Autowired
    FixtureRepository fixtureRepository;

    @Override
    public List<Fixture> saveAll(List<Fixture> items) {
        return fixtureRepository.saveAll(items);
    }
}
