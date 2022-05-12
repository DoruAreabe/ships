package com.example.thymeleaf.model.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Venue {
    private float id;
    private String name;
    private String address;
    private String city;
    private float capacity;
    private String surface;
    private String image;
}
