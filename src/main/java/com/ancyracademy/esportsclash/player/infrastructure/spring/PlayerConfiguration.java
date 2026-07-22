package com.ancyracademy.esportsclash.player.infrastructure.spring;

import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.infrastructure.spring.persistence.jpa.SQLPlayerRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerConfiguration {


    @Bean
    public PlayerRepository playerRepository(EntityManager entityManager){
        return new SQLPlayerRepository(entityManager);
    }


}
