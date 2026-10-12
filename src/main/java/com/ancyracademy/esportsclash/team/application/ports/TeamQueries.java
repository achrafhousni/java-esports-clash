package com.ancyracademy.esportsclash.team.application.ports;

import com.ancyracademy.esportsclash.team.domain.model.viewmodel.TeamViewModel;

public interface TeamQueries {

    TeamViewModel getTeamById(String id);
}
