package com.ancyracademy.esportsclash.team.usecases;

import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.DeleteTeamCommand;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.DeleteTeamCommandHandler;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class DeleteTeamTests {
    InMemoryTeamRepository teamRepository =new InMemoryTeamRepository();

    Team team =new Team("1","Team");

    public DeleteTeamCommandHandler deleteHandler()
    {
        return new DeleteTeamCommandHandler(teamRepository);
    }

    @BeforeEach
    void setUp()
    {

        teamRepository.clear();
        teamRepository.save(team);
    }

@Test
    void shouldDeleteTeam(){
      var command = new DeleteTeamCommand(team.getId());
      deleteHandler().handle(command);

      var deletedTeam = teamRepository.findById(team.getId());
      assert deletedTeam.isEmpty();
    }

    @Test
    void whenTeamDosNotExist_shouldFail(){
        var command = new DeleteTeamCommand("garbage") ;
        var commandHandler = deleteHandler();
        var exception=assertThrows(NotFoundException.class,()->commandHandler.handle(command));
        Assertions.assertEquals("Team with the key garbage not found", exception.getMessage());
        //Assertions.assertEquals("Team  not found", exception.getMessage());

    }
}

