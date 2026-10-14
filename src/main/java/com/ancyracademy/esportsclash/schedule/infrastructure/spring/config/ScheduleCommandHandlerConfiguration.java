package com.ancyracademy.esportsclash.schedule.infrastructure.spring.config;

import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.application.usecases.CancelMatchCommandHandler;
import com.ancyracademy.esportsclash.schedule.application.usecases.OrganizeMatchCommandHandler;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ScheduleCommandHandlerConfiguration {

 @Bean
    public OrganizeMatchCommandHandler organizeMatchCommandHandler(TeamRepository teamRepository,ScheduleDayRepository scheduleDayRepository ){
        return new OrganizeMatchCommandHandler(teamRepository ,scheduleDayRepository);
    }

    @Bean
    public CancelMatchCommandHandler cancelCommandHandler(ScheduleDayRepository scheduleDayRepository){
        return new CancelMatchCommandHandler(scheduleDayRepository);
    }



}
