package com.ancyracademy.esportsclash.team.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.AddPlayerToTeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
 import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class AddPlayerToTeamE2ETests extends IntegrationTests {


   Team team;
   Player player;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;



    @BeforeEach
    public void setUp(){
        teamRepository.clear();
        playerRepository.clear();
        player =new Player("123","Player");

        team =new Team("123","Team");
        teamRepository.save(team);
        playerRepository.save(player);
    }
    @Test
    public void shouldAddPlayerToTeam() throws Exception{

        var dto =new AddPlayerToTeamDTO(player.getId(),team.getId(),"Top");
         mockmvc.perform(MockMvcRequestBuilders.post("/teams/add-player-to-team")
                         . contentType(MediaType.APPLICATION_JSON)
                         .content(objectMapper.writeValueAsString(dto))
                         .header("Authorization",createJWT()))
                  .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var updatedTeam=teamRepository.findById(team.getId()).get();
        Assertions.assertTrue(updatedTeam.hasMember(player.getId(),Role.TOP));

    }

}


