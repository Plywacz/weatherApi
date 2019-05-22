package pl.mplywacz.weatherapi.services;
/*
Author: BeGieU
Date: 22.05.2019
*/

import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;

import java.io.IOException;

public interface LocationService {
    Location registerLocation(LocationDto locationDto) throws IOException;
}
