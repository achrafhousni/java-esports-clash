package com.ancyracademy.esportsclash.team.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.CreateTeamDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class DeleteTeamE2ETests extends IntegrationTests {
    @Autowired
    private TeamRepository teamRepository;


    Team team =new Team("123","Team");

    @BeforeEach
    public void setUp(){
        teamRepository.clear();
        teamRepository.save(team);
    }
    @Test
    public void shouldDeleteTeam() throws Exception{
         mockmvc.perform(MockMvcRequestBuilders.delete("/teams/"+team.getId())
                        .header("Authorization",createJWT()))
                  .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andReturn();

        var teamQuery=teamRepository.findById(team.getId());
        Assertions.assertFalse(teamQuery.isPresent());

    }

}


