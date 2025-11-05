package com.nexapay.esportsclash.player.infrastructure.spring.persistence.jpa;

import com.nexapay.esportsclash.player.domain.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SQLPlayerDataAccessor extends JpaRepository<Player,String> {
}
