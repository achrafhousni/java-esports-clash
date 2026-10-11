package com.ancyracademy.esportsclash.team.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.AddPlayerToTeamDTO;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.RemovePlayerFromTeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class RemovePlayerFromTeamE2ETests extends IntegrationTests {


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
        team.addMember(player.getId(),Role.TOP);
        playerRepository.save(player);
        teamRepository.save(team);

    }
    @Test
    public void shouldRemovePlayerFromTeam() throws Exception{

        var dto =new RemovePlayerFromTeamDTO(player.getId(),team.getId());
         mockmvc.perform(MockMvcRequestBuilders.post("/teams/remove-player-from-team")
                         . contentType(MediaType.APPLICATION_JSON)
                         .content(objectMapper.writeValueAsString(dto))
                         .header("Authorization",createJWT()))
                  .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var updatedTeam=teamRepository.findById(team.getId()).get();
        Assertions.assertFalse(updatedTeam.hasMember(player.getId(),Role.TOP));

    }

}


