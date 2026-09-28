package com.ancyracademy.esportsclash.player;


 import com.ancyracademy.esportsclash.IntegrationTests;
 import com.ancyracademy.esportsclash.auth.domain.model.User;
 import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.PlayerViewModel;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
 import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


public class GetPlayerE2ETests extends IntegrationTests {

    @Autowired
    private PlayerRepository playerRepository;



    @Test
    public void shouldGetPlayer() throws Exception{

        var user =  new User("123","contact@ancryacademy","");
        userRepository.save(user);
        var token= "Bearer "+jwtService.tokenize(user);


        var player=new Player("123","player");
        playerRepository.save(player);
        var result=mockmvc.perform(MockMvcRequestBuilders.get("/players/"+player.getId())
        .header("Authorization",createJWT()))

                .andReturn();
       var viewModel=objectMapper.readValue(result.getResponse().getContentAsString(), PlayerViewModel.class);


     //Assert.assertNotNull(viewModel);
     Assert.assertEquals(player.getName(),viewModel.getName());
     Assert.assertEquals(player.getId(),viewModel.getId());
    }

}
