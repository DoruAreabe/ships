package com.example.thymeleaf.model.responses.fixtures;

import com.example.thymeleaf.model.responses.team.Venue;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FixtureEntity {
    private Integer id;
    private String referee;
    private String timezone;
    private String date;
    private float timestamp;
    Venue VenueObject;
}
