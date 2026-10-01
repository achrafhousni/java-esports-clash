package com.ancyracademy.esportsclash.team.domain.model.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;

import java.util.UUID;

public class DeleteTeamCommandHandler implements Command.Handler<DeleteTeamCommand, IdResponse> {

private final TeamRepository teamRepository;

    public DeleteTeamCommandHandler(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    @Override
    public IdResponse handle(DeleteTeamCommand deleteTeamCommand) {
        var team =  teamRepository.findById(deleteTeamCommand.getId()).orElseThrow(
                ()-> new NotFoundException("Team",
                        deleteTeamCommand.getId())
        );
        teamRepository.delete(team);
         return null;
    }
}
