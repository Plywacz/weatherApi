package pl.mplywacz.weatherapi.services;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;
import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;
import pl.mplywacz.weatherapi.model.Measurement;
import pl.mplywacz.weatherapi.repositories.LocationRepository;
import pl.mplywacz.weatherapi.repositories.MeasurementRepository;

import java.io.IOException;
import java.net.URL;

@Component
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final MeasurementRepository measurementRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    //todo Externalize this
    private final String GET_WEATHER_FROM_API = "http://api.openweathermap.org/data/2.5/weather?appid=5cc43919a828143afb725eab87d845c9&units=metric&q=";

    public LocationServiceImpl(LocationRepository locationRepository, MeasurementRepository measurementRepository) {
        this.locationRepository = locationRepository;
        this.measurementRepository = measurementRepository;
    }

    @Override public Location registerLocation(LocationDto locationDto) throws IOException {
        var measurement = objectMapper.readValue(new URL(GET_WEATHER_FROM_API + locationDto.getCityName()), Measurement.class);

        var location = new Location();
        location.setCityName(locationDto.getCityName());
        location.setFrequency(locationDto.getFrequency());
        location.getMeasurements().add(measurement);

        measurement.setLocation(location);

        var savedLocation = locationRepository.save(location);
        measurementRepository.save(measurement);

        return null;
    }
}
