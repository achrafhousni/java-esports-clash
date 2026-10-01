package com.ancyracademy.esportsclash.team.infrastructure.persistence.ram;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.ram.InMemoryBaseRepository;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;

public class InMemoryTeamRepository extends InMemoryBaseRepository<Team> implements TeamRepository {
}
