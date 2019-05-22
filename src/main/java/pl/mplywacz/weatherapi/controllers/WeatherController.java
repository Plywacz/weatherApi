package pl.mplywacz.weatherapi.controllers;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final ObjectMapper objectMapper = new ObjectMapper();

    //todo Externalize this
    private final String GET_WEATHER_FROM_API = "api.openweathermap.org/data/2.5/weather?appid=5cc43919a828143afb725eab87d845c9&units=metric&q=";


    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Location registerLocation(@RequestBody LocationDto locationDto) {


        return null;
    }


}
