package com.example.thymeleaf.controller.footballapi.response;

import com.example.thymeleaf.model.responses.Paging;
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
public class LeaguesResponse {

    private String get;
    private List<Object> parameters = new ArrayList<>();
    private List<Integer> errors = new ArrayList<>();
    private Integer results;
    private Paging paging;
    private List<ApiLeague> response = new ArrayList<>();

}
