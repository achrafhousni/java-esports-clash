package com.nexapay.esportsclash.player.application.usecases.ports;

import com.nexapay.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.nexapay.esportsclash.player.domain.model.Player;

import java.util.Optional;

public interface PlayerRepository  extends BaseRepository<Player> {

}
