package com.ancyracademy.esportsclash.team.domain.model.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;

public class RemovePlayerFromTeamCommandHandler  implements Command.Handler<RemovePlayerFromTeamCommand,Void> {
    TeamRepository teamRepository;

   public RemovePlayerFromTeamCommandHandler(TeamRepository teamRepository){
       this.teamRepository = teamRepository;
   }
    @Override
    public Void handle(RemovePlayerFromTeamCommand command) {

       var team = teamRepository.findById(command.getTeamId()).orElseThrow(()->new NotFoundException("Team",command.getTeamId()));
       team.removeMember(command.getPlayerId());
        teamRepository.save(team);
        return null;
    }
}
