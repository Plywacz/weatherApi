package pl.mplywacz.weatherapi.dto;
/*
Author: BeGieU
Date: 22.05.2019
*/

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;

/**
 *
 * <p>
 * frequency - frequency of saving weather measurement to db
 */

public class LocationDto {
    @NotEmpty
    private String cityName;

    @Positive
    private Integer frequency;

    public LocationDto() {
    }


    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public Integer getFrequency() {
        return frequency;
    }

    public void setFrequency(Integer frequency) {
        this.frequency = frequency;
    }
}
