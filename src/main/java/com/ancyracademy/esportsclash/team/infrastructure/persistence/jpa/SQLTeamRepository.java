package com.ancyracademy.esportsclash.team.infrastructure.persistence.jpa;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import jakarta.persistence.EntityManager;

public class SQLTeamRepository extends SQLBaseRepository<Team> implements TeamRepository {


    public SQLTeamRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }
}
