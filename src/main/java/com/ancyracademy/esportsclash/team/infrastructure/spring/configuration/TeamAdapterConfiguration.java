package com.ancyracademy.esportsclash.team.infrastructure.spring.configuration;

import com.ancyracademy.esportsclash.team.application.ports.TeamQueries;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.jpa.SQLTeamQueries;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.jpa.SQLTeamRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamAdapterConfiguration {

    @Bean
    public TeamRepository teamRepository(EntityManager entityManager) {
        return new SQLTeamRepository(entityManager);
        //return new InMemoryTeamRepository();
    }

    @Bean
    public TeamQueries teamQueries(EntityManager entityManager){
        return new SQLTeamQueries(entityManager);
    }

 }
