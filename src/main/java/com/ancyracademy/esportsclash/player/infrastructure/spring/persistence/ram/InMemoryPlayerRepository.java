package com.ancyracademy.esportsclash.player.infrastructure.spring.persistence.ram;

import com.ancyracademy.esportsclash.core.infrastructure.persistence.ram.InMemoryBaseRepository;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;

public class InMemoryPlayerRepository  extends InMemoryBaseRepository<Player>  implements PlayerRepository{
//public class InMemoryPlayerRepository implements PlayerRepository {

    /*private Map<String, Player> players=new HashMap<>();
    @Override
    public Optional<Player> findById(String id){
        return Optional.ofNullable(players.get(id));
    }

    @Override
    public void save(Player player){
       this.players.put(player.getId(),player);
    }

    @Override
    public void delete(Player player) {
        this.players.remove(player.getId());
    }*/
}
