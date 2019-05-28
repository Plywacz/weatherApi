package pl.mplywacz.weatherapi.model;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

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

    /*mapped by znaczy ze pole location z klasy Measurement przechowuje klucze tej relacji,
    * jest to tzw dobra praktyka i tak sie robi*/

    @OneToMany(mappedBy = "location",cascade = CascadeType.ALL)
    private Set<Measurement> measurements = new HashSet<>();

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

    public Set<Measurement> getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Set<Measurement> measurements) {
        this.measurements = measurements;
    }
}
