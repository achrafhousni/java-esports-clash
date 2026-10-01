package com.ancyracademy.esportsclash.team.usecases;

import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.CreateTeamCommand;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.CreateTeamCommandHandler;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.ram.InMemoryTeamRepository;
import org.junit.jupiter.api.Test;

public class CreateTeamTests {

    InMemoryTeamRepository teamRepository = new InMemoryTeamRepository();

      CreateTeamCommandHandler createHandler() {
        return new CreateTeamCommandHandler(teamRepository);
    }

    @Test
    void shouldCreateTeam(){
          var command= new CreateTeamCommand("Team");
          var commandHandler = createHandler();
         var response= commandHandler.handle(command);
         var teamQuery= teamRepository.findById(response.getId());
         assert teamQuery.isPresent();
         var team = teamQuery.get();
         assert team.getName().equals("Team");
    }
}
