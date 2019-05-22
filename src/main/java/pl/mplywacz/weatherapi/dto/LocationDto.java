package pl.mplywacz.weatherapi.dto;
/*
Author: BeGieU
Date: 22.05.2019
*/

/**
 *
 * <p>
 * frequency - frequency of saving weather measurement to db
 */

public class LocationDto {
    private String cityName;
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
