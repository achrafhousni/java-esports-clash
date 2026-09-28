package com.ancyracademy.esportsclash.auth;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.auth.infrastructure.spring.RegisterDTO;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

public class RegisterE2ETests extends IntegrationTests {



    @BeforeEach
    public void setup(){
        userRepository.clear();
    }

    @Test
    public void shouldRegisterUser() throws Exception{
        var dto =new RegisterDTO("contact@ancracademy.fr","azerty");
        var result=mockmvc.perform(MockMvcRequestBuilders.post("/auth/register")
                         .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andReturn();
        var idResponse=objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);

        var user= userRepository.findById(idResponse.getId()).get();
        Assert.assertNotNull(user);
        Assert.assertEquals(user.getEmailAddress(),dto.getEmailAddress());

    }


    @Test
    public void whenEmailAddressIsUnavailable_shouldThrow() throws Exception{

        var existingUser = new User("123","contact@ancracademy.fr","azerty");

        userRepository.save(existingUser);
        var dto =new RegisterDTO(existingUser.getEmailAddress(),"azerty");
       mockmvc.perform(MockMvcRequestBuilders.post("/auth/register")
                         .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());



    }
}
