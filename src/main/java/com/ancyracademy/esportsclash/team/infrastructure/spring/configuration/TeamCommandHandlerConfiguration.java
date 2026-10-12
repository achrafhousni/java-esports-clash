package com.ancyracademy.esportsclash.team.infrastructure.spring.configuration;

import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.team.application.ports.TeamQueries;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.application.usecases.*;
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

    @Bean
    public GetTeamByIdCommandHandler getTeamByIdCommandHandler(TeamQueries teamQueries){
        return new GetTeamByIdCommandHandler(teamQueries);
    }





}
