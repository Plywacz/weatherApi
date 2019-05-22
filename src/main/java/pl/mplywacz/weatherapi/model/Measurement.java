package pl.mplywacz.weatherapi.model;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import pl.mplywacz.weatherapi.json.MeasurementDeserialization;

import javax.persistence.*;

@Entity
@Table(name = "measurement")
@JsonDeserialize(using = MeasurementDeserialization.class)
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    private String temp;
    private String temp_min;
    private String humidity;
    private String pressure;
    private String temp_max;

    @ManyToOne
    @JoinColumn(name = "fk_location")
    private Location location;

    public Measurement() {
    }

    public Long getMeasurementId() {
        return measurementId;
    }

    public void setMeasurementId(Long measurementId) {
        this.measurementId = measurementId;
    }


    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(String temp_min) {
        this.temp_min = temp_min;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(String temp_max) {
        this.temp_max = temp_max;
    }
}
