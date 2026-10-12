package com.ancyracademy.esportsclash.team.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.team.domain.model.viewmodel.TeamViewModel;

public class GetTeamByIdCommand implements Command<TeamViewModel> {

    private final String id;

    public GetTeamByIdCommand(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

}
