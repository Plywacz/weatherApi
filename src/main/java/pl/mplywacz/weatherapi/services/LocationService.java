package pl.mplywacz.weatherapi.services;
/*
Author: BeGieU
Date: 22.05.2019
*/

import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;

public interface LocationService {
    Location registerLocation(LocationDto locationDto);
}
