package com.pn.domain.dto;

import lombok.Data;

@Data
public class WeatherParams {
    private String ip;
    private String city;
    private String type;
}
