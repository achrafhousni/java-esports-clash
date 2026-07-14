package fr.ancyracademy.esportsclash.player.infrastructure.spring.persistence.jpa;

import fr.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import fr.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import fr.ancyracademy.esportsclash.player.domain.model.Player;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLPlayerRepository  extends SQLBaseRepository<Player> implements PlayerRepository {

    public  SQLPlayerRepository(EntityManager entityManager) {
        super(entityManager);
    }

    @Override
    public Class<Player> getEntityClass() {
        return Player.class;
    }
}
