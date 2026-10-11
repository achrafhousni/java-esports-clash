package com.ancyracademy.esportsclash.team.infrastructure.spring.dto;

import com.ancyracademy.esportsclash.team.domain.model.Role;

public class RemovePlayerToTeamDTO {
    private String playerId;
    private String teamId;
    public RemovePlayerToTeamDTO() {
    }

    public RemovePlayerToTeamDTO(String playerId, String teamId) {
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
