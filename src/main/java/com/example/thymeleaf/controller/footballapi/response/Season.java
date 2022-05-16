package com.example.thymeleaf.controller.footballapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Season {
    private float year;
    private String start;
    private String end;
    private boolean current;
    private Coverage CoverageObject;
}
