package com.ancyracademy.esportsclash.team.infrastructure.spring.dto;

import com.ancyracademy.esportsclash.team.domain.model.Role;

public class AddPlayerToTeamDTO {
    private String playerId;
    private String teamId;
    private String role;
    public AddPlayerToTeamDTO() {
    }

    public AddPlayerToTeamDTO(String playerId, String teamId, String role) {
        this.playerId = playerId;
        this.teamId = teamId;
        this.role = role;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getTeamId() {
        return teamId;
    }

    public String getRole() {
        return role;
    }
   public Role toRole(){
        return Role.fromString(role);
    }
}
