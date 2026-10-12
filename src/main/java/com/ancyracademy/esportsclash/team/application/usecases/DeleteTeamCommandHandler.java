package com.ancyracademy.esportsclash.team.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;

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
