package com.nexapay.esportsclash.core.infrastructure.persistence.ram;

import com.nexapay.esportsclash.core.infrastructure.persistence.BaseRepository;
import com.nexapay.esportsclash.player.domain.model.BaseEntity;
import com.nexapay.esportsclash.player.domain.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public abstract class InMemoryBaseRepository<T extends BaseEntity> implements BaseRepository<T> {
    private Map<String, T> players=new HashMap<>();
    @Override
    public Optional<T> findById(String id){
        return Optional.ofNullable(players.get(id));
    }

    @Override
    public void save(T entity){
        this.players.put(entity.getId(),entity);
    }

    @Override
    public void delete(T entity) {
        this.players.remove(entity.getId());
    }
}
