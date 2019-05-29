package pl.mplywacz.weatherapi.services;
/*
Author: BeGieU
Date: 22.05.2019
*/

import com.fasterxml.jackson.databind.ObjectMapper;
import org.quartz.*;
import org.springframework.stereotype.Component;
import pl.mplywacz.weatherapi.dto.LocationDto;
import pl.mplywacz.weatherapi.model.Location;
import pl.mplywacz.weatherapi.model.Measurement;
import pl.mplywacz.weatherapi.repositories.LocationRepository;
import pl.mplywacz.weatherapi.repositories.MeasurementRepository;
import pl.mplywacz.weatherapi.scheduler.GetWeatherJob;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

@Component
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final MeasurementRepository measurementRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Date date=new Date();


    private Scheduler scheduler;

    public LocationServiceImpl(LocationRepository locationRepository,
                               MeasurementRepository measurementRepository,
                               Scheduler scheduler) {
        this.locationRepository = locationRepository;
        this.measurementRepository = measurementRepository;
        this.scheduler = scheduler;
    }

    @Override public Location registerLocation(LocationDto locationDto) throws IOException {
        //gets measurement from weather api
        var measurement = objectMapper.readValue(new URL(GET_WEATHER_FROM_API + locationDto.getCityName()), Measurement.class);
        measurement.setMeasurementDate(new java.sql.Date(date.getTime()));

        var location = new Location();
        location.setCityName(locationDto.getCityName());
        location.setFrequency(locationDto.getFrequency());
        location.getMeasurements().add(measurement);

        measurement.setLocation(location);

        //save location with current measurement(that is created when persisting location to db)
        var savedLocation = locationRepository.save(location);
        measurementRepository.save(measurement);

        //schedule job
        scheduleJob(location);

        return savedLocation;
    }

    private void scheduleJob(Location location) {
        JobDetail jobDetail = createMeasurementJob(location);
        Trigger trigger = createTrigger(jobDetail, location.getFrequency());
        try {
            scheduler.scheduleJob(jobDetail, trigger);
        }
        catch (SchedulerException e) {
            e.printStackTrace();
        }
    }

    private JobDetail createMeasurementJob(Location location) {
        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("location", location);

        return JobBuilder.newJob(GetWeatherJob.class)
                .withDescription("Getting measurements for " + location.getCityName() + " with id" + location.getLocationId())
                .withIdentity(location.getLocationId().toString())
                .usingJobData(jobDataMap)
                .storeDurably()
                .build();

    }

    private Trigger createTrigger(JobDetail jobDetail, Integer frequency) {
        return TriggerBuilder.newTrigger()
                .forJob(jobDetail)
                .withDescription("get weather Trigger")
                .withSchedule(
                        SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(frequency)
                                .withRepeatCount(DEFAULT_REPEAT_COUNT)
                ).build();
    }
}
