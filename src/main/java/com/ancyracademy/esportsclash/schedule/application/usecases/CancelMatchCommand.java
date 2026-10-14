package com.ancyracademy.esportsclash.schedule.application.usecases;

import an.awesome.pipelinr.Command;

public class CancelMatchCommand  implements Command<Void> {
  private final String matchId;


    public CancelMatchCommand(String matchId) {
        this.matchId = matchId;
    }

    public String getMatchId() {
        return matchId;
    }
}
