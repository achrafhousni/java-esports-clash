package com.ancyracademy.esportsclash.player.infrastructure.spring.persistence.jpa;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import jakarta.persistence.EntityManager;

public class SQLPlayerRepository  extends SQLBaseRepository<Player> implements PlayerRepository {

    public  SQLPlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Player> getEntityClass() {
        return Player.class;
    }
}
