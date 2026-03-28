package fr.ancyracademy.esportsclash.player.infrastructure.spring.persistence.jpa;

import fr.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import fr.ancyracademy.esportsclash.player.domain.model.Player;

import java.util.Optional;

public class SQLPlayerRepository implements PlayerRepository {
    private final SQLPlayerDataAccessor dataAccessor;

    public SQLPlayerRepository(SQLPlayerDataAccessor dataAccessor) {
        this.dataAccessor = dataAccessor;
    }

    @Override
    public Optional<Player> findById(String id) {
      /* var sqlPlayerQuery=dataAccessor.findById(id);
        if(sqlPlayerQuery.isEmpty()) return null;
        var sqlPlayer=sqlPlayerQuery.get();
        var player=new Player(sqlPlayer.getId(),sqlPlayer.getName());
        return player;*/
        return dataAccessor.findById(id);
    }

    @Override
    public void save(Player player) {
          //var sqlPlayer=new SQLPlayer(player.getId(), player.getName());
          dataAccessor.save(player);
    }
}
