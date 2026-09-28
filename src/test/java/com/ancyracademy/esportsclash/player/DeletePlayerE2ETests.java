package com.ancyracademy.esportsclash.player;

 import com.ancyracademy.esportsclash.IntegrationTests;
 import com.ancyracademy.esportsclash.PostgreSQLTestConfiguration;
 import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;


public class DeletePlayerE2ETests extends IntegrationTests {


    @Autowired
    private PlayerRepository playerRepository;
    @Test
    public void shouldDeletePlayer() throws Exception{
        var existingPlayer=new Player("123","player");
        playerRepository.save(existingPlayer);

       mockmvc.perform(MockMvcRequestBuilders.delete("/players/"+existingPlayer.getId())
                       .header("Authorization",createJWT())
                )

                .andExpect(MockMvcResultMatchers.status().isNoContent());


        var playerQuery=playerRepository.findById(existingPlayer.getId());
        //Assert.assertNotNull(player);
        Assert.assertTrue(playerQuery.isEmpty());

    }


    @Test
    public void whenPlayerDoesNotExist_shouldFail() throws Exception{
        mockmvc.perform(MockMvcRequestBuilders.delete("/players/garbage")
                        .header("Authorization",createJWT())
                )
                .andExpect(MockMvcResultMatchers.status().isNotFound());



    }
}
