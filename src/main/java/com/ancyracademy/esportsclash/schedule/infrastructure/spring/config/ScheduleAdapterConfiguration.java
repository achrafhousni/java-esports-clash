package com.ancyracademy.esportsclash.schedule.infrastructure.spring.config;

import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.infrastructure.persistence.ram.InMemoryScheduleDayRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleAdapterConfiguration {

    @Bean
    public ScheduleDayRepository scheduleAdapter(){
        return new InMemoryScheduleDayRepository();
    };
}
