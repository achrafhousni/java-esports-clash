package com.ancyracademy.esportsclash.team.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.BadRequestException;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;

public class AddPlayerToTeamCommandHandler implements Command.Handler<AddPlayerToTeamCommand, Void> {

    private final PlayerRepository playerRepository;
    private final TeamRepository teamRepository;

    public AddPlayerToTeamCommandHandler(PlayerRepository playerRepository, TeamRepository teamRepository) {
        this.playerRepository = playerRepository;
        this.teamRepository = teamRepository;
    }

    @Override
    public Void handle(AddPlayerToTeamCommand command) {
        var player = playerRepository.findById(command.getPlayerId())
                .orElseThrow(()->new NotFoundException("Player",command.getPlayerId()));

        var team = teamRepository.findById(command.getTeamId())
                .orElseThrow(()->new NotFoundException("Team",command.getTeamId()));

        var teamPlayerBelongsTo = teamRepository.findByPlayerId(command.getPlayerId());

        if(teamPlayerBelongsTo.isPresent()){
            throw new BadRequestException("Player already in team");
        }

        team.addMember(player.getId(),command.getRole());
        teamRepository.save(team);
        return null;
    }
}
