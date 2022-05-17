package com.example.thymeleaf.model.responses.fixtures;

import com.example.thymeleaf.model.responses.Paging;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class FixtureResponse {
    private String get;
    FixtureParameters ParametersObject;
    List<Integer> errors = new ArrayList<>();
    private float results;
    Paging paging;
    List<FixtureResponseEntity> response = new ArrayList<>();
}
