package com.example.thymeleaf.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "teams")
public class Team {
    @Id
    private float id;
    private String name;
    private String code;
    private String country;
    @ManyToOne
    @JoinColumn(name = "league_id")
    private League league;
}
