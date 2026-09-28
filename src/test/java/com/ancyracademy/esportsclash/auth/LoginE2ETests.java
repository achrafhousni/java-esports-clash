package com.ancyracademy.esportsclash.auth;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import com.ancyracademy.esportsclash.auth.domain.model.LoggedInUserViewModel;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.auth.infrastructure.spring.LoginDTO;
import com.ancyracademy.esportsclash.auth.infrastructure.spring.RegisterDTO;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


public class LoginE2ETests extends IntegrationTests {

    @Autowired
     private PasswordHasher passwordHasher;

    @BeforeEach
    public void setup(){
        userRepository.clear();
         var user=new User("123","contact@ancracademy.fr", passwordHasher.hash("azerty"));
        userRepository.save(user);
    }

    @Test
    public void shouldLogTheUserIn() throws Exception{
        var dto= new LoginDTO("contact@ancracademy.fr","azerty");
         var result=mockmvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        LoggedInUserViewModel user=objectMapper.readValue(result.getResponse().getContentAsString(), LoggedInUserViewModel.class);
        Assert.assertEquals(user.getId()    ,"123");
        Assert.assertEquals(user.getEmailAddress(),"contact@ancracademy.fr");

    }


    @Test
    public void whenEmailAddressIsUnavailable_shouldThrow() throws Exception{

        var existingUser = new User("123","not-available@ancracademy.fr","azerty");
        var dto =new RegisterDTO(existingUser.getEmailAddress(),"azerty");
       mockmvc.perform(MockMvcRequestBuilders.post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto)))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }
}
