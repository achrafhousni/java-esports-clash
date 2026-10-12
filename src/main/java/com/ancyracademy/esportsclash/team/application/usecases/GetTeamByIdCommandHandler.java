package com.ancyracademy.esportsclash.team.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.team.application.ports.TeamQueries;
import com.ancyracademy.esportsclash.team.domain.model.viewmodel.TeamViewModel;

public class GetTeamByIdCommandHandler  implements Command.Handler<GetTeamByIdCommand, TeamViewModel> {

    private final TeamQueries teamQueries;

    public GetTeamByIdCommandHandler(TeamQueries teamQueries) {
        this.teamQueries = teamQueries;
    }


    @Override
    public TeamViewModel handle(GetTeamByIdCommand getTeamByIdCommand) {
        return teamQueries.getTeamById(getTeamByIdCommand.getId());
    }
}
