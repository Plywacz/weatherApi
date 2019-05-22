package pl.mplywacz.weatherapi.model;

import javax.persistence.*;
import java.util.LinkedList;
import java.util.List;

/*
Author: BeGieU
Date: 22.05.2019
*/
@Entity
@Table(name = "location")
public class Location {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long locationId;

    private String cityName;
    private Integer frequency;

    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    private List<Measurement> measurements = new LinkedList<>();

    public Location() {
    }

    public Long getLocationId() {
        return locationId;
    }

    public void setLocationId(Long locationId) {
        this.locationId = locationId;
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

    public List<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(List<Measurement> measurements) {
        this.measurements = measurements;
    }
}
