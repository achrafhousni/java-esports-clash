package com.ancyracademy.esportsclash.auth;

import com.ancyracademy.esportsclash.auth.application.services.jwtservice.ConcreteJwtService;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class JwtServiceTest {

    @Test
    void shouldTokenizeTheUser() {
        var jwtService= new ConcreteJwtService("your-secret-key-here-minimum-256-bits",60);
        var user = new User("123", "test@test.com","azerrty");
        var token = jwtService.tokenize(user);
        var authUser = jwtService.parse(token);
        Assertions.assertNotNull(authUser);
        Assertions.assertEquals(user.getId(), authUser.getId());
        Assertions.assertEquals(user.getEmailAddress(), authUser.getEmailAddress());
    }
}
