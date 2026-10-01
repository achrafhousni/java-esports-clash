package com.ancyracademy.esportsclash.team.usecases;

import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.player.infrastructure.spring.persistence.ram.InMemoryPlayerRepository;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.AddPlayerToTeamCommand;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.AddPlayerToTeamCommandHandler;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class AddPlayerToTeamTests {
    InMemoryPlayerRepository playerRepository = new InMemoryPlayerRepository();
    InMemoryTeamRepository teamRepository =new InMemoryTeamRepository();

    Team team =new Team("1","Team");
    Player player =new Player("1","Player");

    public AddPlayerToTeamCommandHandler createHandler()
    {
        return new AddPlayerToTeamCommandHandler(playerRepository,teamRepository);
    }

    @BeforeEach
    void setUp()
    {

        teamRepository.clear();
        playerRepository.clear();
        teamRepository.save(team);
        playerRepository.save(player);
    }

@Test
    void shouldAddPlayerToTeam(){
      var command = new AddPlayerToTeamCommand(player.getId(),team.getId(), Role.TOP);
      var commandHandler = createHandler();
      commandHandler.handle(command);

      var teamQuery = teamRepository.findById(command.getTeamId()).get();
      Assertions.assertTrue(teamQuery.hasMember(command.getPlayerId(),command.getRole()));
      //Assert.assertTrue(teamQuery.hasMember(command.getPlayerId(),command.getRole()));

}

    @Test
    void whenPlayerDoesNotExist_shouldThrow(){
        var command = new AddPlayerToTeamCommand("garbage",team.getId(), Role.TOP);
        var commandHandler = createHandler();
         var exception = assertThrows(NotFoundException.class,()->commandHandler.handle(command));
        Assertions.assertEquals("Player with the key garbage not found", exception.getMessage());
    }


    @Test
    void whenTeamDoesNotExist(){
        var command = new AddPlayerToTeamCommand(player.getId(),"garbage", Role.TOP);
        var commandHandler = createHandler();
        var exception = assertThrows(NotFoundException.class,()->commandHandler.handle(command));
        Assertions.assertEquals("Team with the key garbage not found", exception.getMessage());
    }

}

