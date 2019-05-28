package pl.mplywacz;
/*
Author: BeGieU
Date: 23.05.2019
*/


import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import pl.mplywacz.weatherapi.scheduler.GetWeatherJob;

public class QuartzTest {

    @Test
    public void quartzExample() throws SchedulerException {
        JobDetail job = JobBuilder.newJob(GetWeatherJob.class)
                .withIdentity("dummyJobName", "group1").build();


        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("dummyTriggerName", "group1")
                .withSchedule(
                        SimpleScheduleBuilder.simpleSchedule()
                                .withIntervalInSeconds(5).repeatForever())
                .build();

        // schedule it
        Scheduler scheduler = new StdSchedulerFactory().getScheduler();
        scheduler.start();
        scheduler.scheduleJob(job, trigger);

    }


}
