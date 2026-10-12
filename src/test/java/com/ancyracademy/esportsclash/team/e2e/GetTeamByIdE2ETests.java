package com.ancyracademy.esportsclash.team.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.PlayerViewModel;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.viewmodel.TeamViewModel;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.RemovePlayerFromTeamDTO;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class GetTeamByIdE2ETests extends IntegrationTests {


   Team team;
   Player player;

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;



    @BeforeEach
    public void setUp(){

        player =new Player("123","Player");

        team =new Team("123","Team");
        team.addMember(player.getId(),Role.TOP);
        playerRepository.save(player);
        teamRepository.save(team);
        clearDataBaseCache();

    }
    @Test
    public void shouldGetTheTeam() throws Exception{

          var result = mockmvc.perform(MockMvcRequestBuilders.get("/teams/"+team.getId())
                           .header("Authorization",createJWT()))
                  .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var viewModel=objectMapper.readValue(result.getResponse().getContentAsString(), TeamViewModel.class);


        Assertions.assertEquals(team.getId(),viewModel.getId());
        Assertions.assertEquals(team.getName(),viewModel.getName());

        var firstMember= viewModel.getMembers().getFirst();
       Assertions.assertEquals(player.getId(),firstMember.getPlayerId());
       Assertions.assertEquals(player.getName(),firstMember.getPlayerName());
       Assertions.assertEquals("TOP",firstMember.getRole());



    }

}


