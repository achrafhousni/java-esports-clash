package com.ancyracademy.esportsclash.team.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.player.infrastructure.spring.CreatePlayerDTO;
import com.ancyracademy.esportsclash.team.domain.model.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.CreateTeamDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class CreateTeamE2ETests  extends IntegrationTests {
    @Autowired
    private TeamRepository teamRepository;
    @Test
    public void shouldCreatePlayer() throws Exception{
        var dto =new CreateTeamDTO("team");
        var result=mockmvc.perform(MockMvcRequestBuilders.post("/teams")
                        .header("Authorization",createJWT())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        var idResponse=objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);

        var team=teamRepository.findById(idResponse.getId()).get();
        Assert.assertNotNull(team);
        Assert.assertEquals(dto.getName(),team.getName());

    }

}


