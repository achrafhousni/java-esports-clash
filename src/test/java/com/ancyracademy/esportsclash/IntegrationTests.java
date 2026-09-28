package com.ancyracademy.esportsclash;

import com.ancyracademy.esportsclash.auth.application.services.jwtservice.JwtService;
import com.ancyracademy.esportsclash.auth.application.ports.UserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.shaded.com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
@Import(PostgreSQLTestConfiguration.class)
@Transactional
public class IntegrationTests {


    @Autowired
    protected MockMvc mockmvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Autowired
    protected UserRepository userRepository;
    @Autowired
    protected JwtService jwtService;

    protected String createJWT(){
        var user=userRepository.findByEmailAddress("contact@ancryacademy").orElse(null);

if(user==null){
    user =  new User("123","contact@ancryacademy","");
        userRepository.save(user);
    }
        return "Bearer "+jwtService.tokenize(user);
    }
}
