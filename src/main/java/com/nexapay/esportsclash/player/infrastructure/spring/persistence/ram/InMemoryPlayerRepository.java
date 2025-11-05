package com.nexapay.esportsclash.player.infrastructure.spring.persistence.ram;

import com.nexapay.esportsclash.core.infrastructure.persistence.ram.InMemoryBaseRepository;
import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.nexapay.esportsclash.player.domain.model.Player;



public class InMemoryPlayerRepository extends InMemoryBaseRepository<Player> implements PlayerRepository {


}
