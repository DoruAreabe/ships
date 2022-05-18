package com.example.thymeleaf.model.entity;

import com.example.thymeleaf.model.responses.fixtures.FixtureResponseEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Fixture {
    @Id
    Integer id;
    @ManyToOne
    @JoinColumn(name = "home_id")
    Team home;
    @ManyToOne
    @JoinColumn(name = "away_id")
    Team away;
    String date;
    String score;

    public Fixture(FixtureResponseEntity entity) {
        id=entity.getFixture().getId();
        home = entity.getTeams().getHome();
        away = entity.getTeams().getAway();
        date = entity.getFixture().getDate().replace("T", " ").split("\\+")[0];
        if (entity.getGoals().getHome() != null) score = entity.getGoals().getHome() +"-"+entity.getGoals().getAway();
    }
}
