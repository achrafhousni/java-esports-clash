package com.ancyracademy.esportsclash.team.application.ports;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.ancyracademy.esportsclash.team.domain.model.Team;

import java.util.Optional;

public interface TeamRepository  extends BaseRepository<Team> {

     Optional<Team> findByPlayerId(Long playerId);
}
