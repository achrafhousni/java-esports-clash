package com.nexapay.esportsclash.player;


 import com.nexapay.esportsclash.PostgreSQLTestConfiguration;

 import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
 import com.nexapay.esportsclash.player.domain.model.viewmodel.IdResponse;
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
public class CreatePlayerE2ETests {

    @Autowired
    private MockMvc mockmvc;
    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PlayerRepository playerRepository;
    @Test
    public void shouldCreatePlayer() throws Exception{
       var dto =new CreatePlayerDTO("player");
       var result=mockmvc.perform(MockMvcRequestBuilders.post("/players")
               .contentType(MediaType.APPLICATION_JSON)
               .content(objectMapper.writeValueAsString(dto)))
               .andExpect(MockMvcResultMatchers.status().isCreated())
               .andReturn();
       var idResponse=objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);

       var player=playerRepository.findById(idResponse.getId()).get();
        Assert.assertNotNull(player);
        Assert.assertEquals(dto.getName(),player.getName());

    }

}
