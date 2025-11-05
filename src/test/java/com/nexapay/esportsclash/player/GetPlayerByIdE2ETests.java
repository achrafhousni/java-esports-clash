package com.nexapay.esportsclash.player;


import com.nexapay.esportsclash.PostgreSQLTestConfiguration;
import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.nexapay.esportsclash.player.domain.model.Player;
import com.nexapay.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.nexapay.esportsclash.player.domain.model.viewmodel.PlayerViewModel;
import com.nexapay.esportsclash.player.infrastructure.spring.CreatePlayerDTO;
import org.junit.Assert;
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
public class GetPlayerByIdE2ETests {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;
    @Test
    public void shouldGetPlayerById() throws Exception{

        var player= new Player("123","player");
        playerRepository.save(player);
        var result=mockmvc.perform(MockMvcRequestBuilders.get("/players/"+ player.getId()))

              // .andExpect(MockMvcResultMatchers.status().isOk())
               .andReturn();
       var viewModel=objectMapper.readValue(result.getResponse().getContentAsString(), PlayerViewModel.class);

        Assert.assertEquals(viewModel.getId(),player.getId());

        Assert.assertEquals(viewModel.getName(),player.getName());

    }

}
