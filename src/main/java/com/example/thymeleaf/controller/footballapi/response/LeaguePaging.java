package com.example.thymeleaf.controller.footballapi.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LeaguePaging {
    private float current;
    private float total;
}
