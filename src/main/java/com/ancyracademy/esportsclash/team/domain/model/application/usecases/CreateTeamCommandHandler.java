package com.ancyracademy.esportsclash.team.domain.model.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;

import java.util.UUID;

public class CreateTeamCommandHandler implements Command.Handler<CreateTeamCommand, IdResponse> {

private final TeamRepository teamRepository;

    public CreateTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public IdResponse handle(CreateTeamCommand createTeamCommand) {
        var team = new Team(UUID.randomUUID().toString(),createTeamCommand.getName());
        teamRepository.save(team);
        return new IdResponse(team.getId());
    }
}
