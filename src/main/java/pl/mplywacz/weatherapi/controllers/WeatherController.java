package pl.mplywacz.weatherapi.controllers;
/*
Author: BeGieU
Date: 22.05.2019
*/

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;
import pl.mplywacz.weatherapi.services.LocationService;

import javax.validation.Valid;
import java.io.IOException;

@RestController
@RequestMapping("/api")
public class WeatherController {

    private final LocationService locationService;

    public WeatherController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public @ResponseBody Location registerLocation(@Valid @RequestBody LocationDto locationDto) throws IOException {
        return locationService.registerLocation(locationDto);
    }


}
