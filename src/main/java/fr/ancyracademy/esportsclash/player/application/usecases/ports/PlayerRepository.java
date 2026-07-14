package fr.ancyracademy.esportsclash.player.application.usecases.ports;

import fr.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;
import fr.ancyracademy.esportsclash.player.domain.model.Player;

import java.util.Optional;

public interface PlayerRepository extends BaseRepository<Player> {

}
