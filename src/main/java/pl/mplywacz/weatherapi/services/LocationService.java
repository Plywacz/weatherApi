package pl.mplywacz.weatherapi.services;
/*
Author: BeGieU
Date: 22.05.2019
*/

import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;

import java.io.IOException;

public interface LocationService {
    //todo Externalize this to the app properties
    String GET_WEATHER_FROM_API = "http://api.openweathermap.org/data/2.5/weather?appid=5cc43919a828143afb725eab87d845c9&units=metric&q=";
    Integer DEFAULT_REPEAT_COUNT = 5;

    Location registerLocation(LocationDto locationDto) throws IOException;
}
