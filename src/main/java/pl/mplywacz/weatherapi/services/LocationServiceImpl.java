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
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collections;
import java.util.TreeSet;

@Component
public class LocationServiceImpl implements LocationService {
    private final LocationRepository locationRepository;
    private final MeasurementRepository measurementRepository;
    private final ObjectMapper objectMapper = new ObjectMapper();

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
        measurement.setMeasurementDate(new Timestamp(System.currentTimeMillis()));

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
                                //.withRepeatCount(DEFAULT_REPEAT_COUNT)
                                .repeatForever()
                ).build();
    }

    /**
     * @param count number of last measurements that will be consult in counting average value
     * @return average value of last count measurements
     */
    public Double getAverageMeasurementValue(Long id, Integer count) {
        //todo split this code to private methods
        var locationOptional = locationRepository.findById(id);
        if (locationOptional.isEmpty()) {
            throw new IllegalArgumentException("Illegal Argument, entity with given id doesnt exist in db");
        }
        var location = locationOptional.get();

        //sorting hashSet
        var measurements = location.getMeasurements();
        if (measurements.size() < count) {
            throw new IllegalArgumentException("given location has not enough measurements to count average temp with given count");
        }
        var measurementsList = new ArrayList<Measurement>(measurements);
        Collections.sort(measurementsList, (obj1, obj2) -> {
            if (obj1.getMeasurementDate() == null || obj2.getMeasurementDate() == null) {
                return 0;
            }
            return obj1.getMeasurementDate().compareTo(obj2.getMeasurementDate());
        });

        //counting average
        var indexOfLastElement = measurementsList.size() - 1;
        var sum = 0.0;
        for (int i = 0; i < count; i++, indexOfLastElement--) {
            sum = sum + Double.parseDouble(measurementsList.get(indexOfLastElement).getTemp());
        }

        return sum/count;
    }
}
