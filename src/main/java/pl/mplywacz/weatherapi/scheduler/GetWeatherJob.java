package pl.mplywacz.weatherapi.scheduler;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.stereotype.Component;
import pl.mplywacz.weatherapi.model.Location;
import pl.mplywacz.weatherapi.model.Measurement;
import pl.mplywacz.weatherapi.repositories.LocationRepository;
import pl.mplywacz.weatherapi.repositories.MeasurementRepository;

import java.io.IOException;
import java.net.URL;

import static pl.mplywacz.weatherapi.services.LocationService.GET_WEATHER_FROM_API;

@Component
public class GetWeatherJob implements Job {
    private final MeasurementRepository measurementRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public GetWeatherJob( MeasurementRepository measurementRepository) {
        this.measurementRepository = measurementRepository;
    }

    public void execute(JobExecutionContext context) throws JobExecutionException {
        try {

            JobDataMap jobDataMap = context.getMergedJobDataMap();

            //todo ensure you can cast this !!!
            var location = (Location) jobDataMap.get("location");

            var measurement = objectMapper.readValue(new URL(GET_WEATHER_FROM_API + location.getCityName()), Measurement.class);

            measurement.setLocation(location);
            measurementRepository.save(measurement);

        }
        catch (JsonParseException e) {
            e.printStackTrace();
        }
        catch (JsonMappingException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

}
