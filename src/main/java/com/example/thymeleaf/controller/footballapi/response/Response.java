package com.example.thymeleaf.controller.footballapi.response;

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
public class Response {

    private String get;
    private List<Object> parameters = new ArrayList<>();
    private List<Integer> errors = new ArrayList<>();
    private Integer results;
    private LeaguePaging leaguePaging;
    private List<ApiLeague> response = new ArrayList<>();

}
