package pl.mplywacz.weatherapi.model;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import pl.mplywacz.weatherapi.json.MeasurementDeserialization;
import pl.mplywacz.weatherapi.json.MeasurementSerialization;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "measurement")
@JsonDeserialize(using = MeasurementDeserialization.class)
@JsonSerialize(using = MeasurementSerialization.class)
public class Measurement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long measurementId;

    private String temp;
    private String tempMin;
    private String humidity;
    private String pressure;
    private String tempMax;

    private String weatherDescription;

    @ManyToOne
    @JoinColumn(name = "fk_location")
    private Location location;

    private Date measurementDate;

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

    public String getTempMin() {
        return tempMin;
    }

    public void setTempMin(String tempMin) {
        this.tempMin = tempMin;
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

    public String getTempMax() {
        return tempMax;
    }

    public void setTempMax(String tempMax) {
        this.tempMax = tempMax;
    }

    public String getWeatherDescription() {
        return weatherDescription;
    }

    public void setWeatherDescription(String weatherDescription) {
        this.weatherDescription = weatherDescription;
    }

    public Date getMeasurementDate() {
        return measurementDate;
    }

    public void setMeasurementDate(Date measurementDate) {
        this.measurementDate = measurementDate;
    }
}
