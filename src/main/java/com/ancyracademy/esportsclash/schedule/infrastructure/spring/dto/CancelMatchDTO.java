package com.ancyracademy.esportsclash.schedule.infrastructure.spring.dto;

public class CancelMatchDTO {

    private String matchId;

    public CancelMatchDTO() {
    }

    public CancelMatchDTO(String match) {
        this.matchId = match;
    }

    public String getMatchId() {
        return matchId;
    }
}
