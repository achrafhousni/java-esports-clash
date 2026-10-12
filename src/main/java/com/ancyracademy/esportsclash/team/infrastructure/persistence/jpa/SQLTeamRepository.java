package com.ancyracademy.esportsclash.team.infrastructure.persistence.jpa;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLTeamRepository extends SQLBaseRepository<Team> implements TeamRepository {


    public SQLTeamRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Team> getEntityClass() {
        return Team.class;
    }

    @Override
    public Optional<Team> findByPlayerId(Long playerId) {
  ///  memebers is loaded eagerly
        var query =entityManager.createQuery("SELECT t FROM Team t JOIN t.members m WHERE m.playerId = :playerId", Team.class);
        query.setParameter("playerId", playerId);
        return query.getResultList().stream().findFirst();
    }
}
