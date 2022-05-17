package com.example.thymeleaf.model.responses.fixtures;

import com.example.thymeleaf.model.entity.Team;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Teams {
    Team home;
    Team away;
}