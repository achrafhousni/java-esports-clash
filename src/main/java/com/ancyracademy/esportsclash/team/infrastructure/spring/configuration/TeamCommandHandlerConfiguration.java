package com.ancyracademy.esportsclash.team.infrastructure.spring.configuration;

import com.ancyracademy.esportsclash.player.application.usecases.CreatePlayerCommandHandler;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.AddPlayerToTeamCommand;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.AddPlayerToTeamCommandHandler;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.CreateTeamCommandHandler;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.RemovePlayerFromTeamCommandHandler;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.DeleteTeamCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TeamCommandHandlerConfiguration {

    @Bean
    public CreateTeamCommandHandler createTeamHandler(TeamRepository repository){
        return new CreateTeamCommandHandler(repository);
    }

    @Bean
    public DeleteTeamCommandHandler deleteTeamCommandHandler(TeamRepository repository){
        return new DeleteTeamCommandHandler(repository);
    }

    @Bean
    public AddPlayerToTeamCommandHandler addPlayerToTeamCommandHandler(PlayerRepository repository, TeamRepository teamRepository){
        return new AddPlayerToTeamCommandHandler(repository,teamRepository);
    }

    @Bean
    public RemovePlayerFromTeamCommandHandler removePlayerFromTeamCommandHandler( TeamRepository teamRepository){
        return new RemovePlayerFromTeamCommandHandler(teamRepository);
    }



}
