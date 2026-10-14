package com.ancyracademy.esportsclash.schedule.infrastructure.spring.config;

import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.infrastructure.persistence.jpa.SQLScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.infrastructure.persistence.ram.InMemoryScheduleDayRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleAdapterConfiguration {

    @Bean
    public ScheduleDayRepository scheduleAdapter(EntityManager entityManager){

        return new SQLScheduleDayRepository(entityManager);
    };
}
