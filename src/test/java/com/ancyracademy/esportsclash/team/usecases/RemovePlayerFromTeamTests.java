package com.ancyracademy.esportsclash.team.usecases;

import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.RemovePlayerFromTeamCommand;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.RemovePlayerFromTeamCommandHandler;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class RemovePlayerFromTeamTests {

    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();
    Team team;
    String playerId="1";
    Role role =Role.TOP;
    public RemovePlayerFromTeamCommandHandler deleteHandler() {
        return new RemovePlayerFromTeamCommandHandler(teamRepository);
    }

    @BeforeEach
    void setUp()
    {

        teamRepository.clear();
        team = new Team("1", "Team");
        team.addMember(playerId , role);
        teamRepository.save(team);
        
    }

@Test
void shouldRemovePlayerFromTeam() {


    var command = new RemovePlayerFromTeamCommand(playerId, team.getId());
    var commandHandler = deleteHandler();
    commandHandler.handle(command);

    var teamQuery = teamRepository.findById(command.getTeamId()).get();
    Assertions.assertFalse(teamQuery.hasMember(command.getPlayerId(), role));

}


    @Test
    void whenTeamDoesNotExist_ShouldThrow(){
        var command = new RemovePlayerFromTeamCommand(playerId, "garbage");
        var commandHandler = deleteHandler();
        var exception = assertThrows(NotFoundException.class,()->commandHandler.handle(command));
        Assertions.assertEquals("Team with the key garbage not found", exception.getMessage());
    }

}

