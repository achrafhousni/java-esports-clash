package fr.ancyracademy.esportsclash.player.infrastructure.spring.persistence.ram;

import fr.ancyracademy.esportsclash.core.infrastructure.persistence.ram.InMemoryBaseRepository;
import fr.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import fr.ancyracademy.esportsclash.player.domain.model.Player;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
