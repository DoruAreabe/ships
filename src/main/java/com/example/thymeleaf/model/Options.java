package com.example.thymeleaf.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Options {
    private List<String> selected = new ArrayList<>();
    private List<String> activeLeagues = new ArrayList<>();
}
