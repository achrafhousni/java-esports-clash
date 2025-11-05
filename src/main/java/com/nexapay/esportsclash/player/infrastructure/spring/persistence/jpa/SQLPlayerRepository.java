package com.nexapay.esportsclash.player.infrastructure.spring.persistence.jpa;

import com.nexapay.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.nexapay.esportsclash.player.domain.model.Player;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLPlayerRepository extends SQLBaseRepository<Player> implements PlayerRepository {
    public SQLPlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Player> getEntityClass() {
        return Player.class;
    }
}
