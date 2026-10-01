package com.ancyracademy.esportsclash.team.infrastructure.spring.configuration;

import com.ancyracademy.esportsclash.player.application.usecases.CreatePlayerCommandHandler;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.CreateTeamCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamCommandHandlerConfiguration {

    @Bean
    public CreateTeamCommandHandler createTeamHandler(TeamRepository repository){
        return new CreateTeamCommandHandler(repository);
    }
}
