package com.ancyracademy.esportsclash.team.domain.model.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.team.domain.model.Role;

public class RemovePlayerFromTeamCommand implements Command<Void> {

    private final String playerId;
    private final String teamId;

    public RemovePlayerFromTeamCommand(String playerId, String teamId ) {
        this.playerId = playerId;
        this.teamId = teamId;
     }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamId() {
        return teamId;
    }

 }
