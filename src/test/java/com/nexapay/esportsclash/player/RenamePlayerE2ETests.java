package com.nexapay.esportsclash.player;

import com.nexapay.esportsclash.PostgreSQLTestConfiguration;
import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.nexapay.esportsclash.player.domain.model.Player;
import com.nexapay.esportsclash.player.infrastructure.spring.RenamePlayerDTO;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLTestConfiguration.class)
@Transactional
public class RenamePlayerE2ETests {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;
    @Test
    public void shouldRenamePlayer() throws Exception{
        var existingPlayer=new Player("123","player");
         playerRepository.save(existingPlayer);

        var dto =new RenamePlayerDTO("player");
        var result=mockmvc.perform(MockMvcRequestBuilders.patch("/players/"+existingPlayer.getId()+"/rename")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk());


        var player=playerRepository.findById(existingPlayer.getId()).get();
        //Assert.assertNotNull(player);
        Assertions.assertEquals(dto.getName(), player.getName());

    }


    @Test
    public void whenPlayerDoesNotExist_shouldFail() throws Exception{


        var dto =new RenamePlayerDTO("player");
         mockmvc.perform(MockMvcRequestBuilders.patch("/players/garbage/rename")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
               ;



    }
}
