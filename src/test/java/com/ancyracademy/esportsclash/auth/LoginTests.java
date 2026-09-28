package com.ancyracademy.esportsclash.auth;

import com.ancyracademy.esportsclash.auth.application.services.jwtservice.ConcreteJwtService;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.BcryptPasswordHasher;
import com.ancyracademy.esportsclash.auth.application.usecases.LoginCommand;
import com.ancyracademy.esportsclash.auth.application.usecases.LoginCommandHandler;
import com.ancyracademy.esportsclash.auth.infrastructure.persistence.ram.InMemoryUserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.LoggedInUserViewModel;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.core.domain.exceptions.BadRequestException;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

public class LoginTests {

    private final InMemoryUserRepository userRepository = new InMemoryUserRepository();
    private final ConcreteJwtService jwtService = new ConcreteJwtService("your-secret-key-here-minimum-256-bits",60);

    private final User user = new User("123","contact@anvryacademy.fr",new BcryptPasswordHasher().hash("password"));

    @BeforeEach
    void setUp(){
        userRepository.clear();
        userRepository.save(user);
    }

    LoginCommandHandler createHandler(){
        return new LoginCommandHandler(userRepository,jwtService,new BcryptPasswordHasher());
    }

    @Nested
    class happyPath {

        @Test
        void shouldReturnTheUser(){


            var command= new LoginCommand("contact@anvryacademy.fr","password");
            var commandHandler = createHandler();

            LoggedInUserViewModel result= commandHandler.handle(command);

            Assertions.assertEquals(result.getId(),user.getId());
            Assertions.assertEquals(result.getEmailAddress(),user.getEmailAddress());

            var authenticatedUser= jwtService.parse(result.getToken());

            Assertions.assertEquals(result.getId(),authenticatedUser.getId());
            Assertions.assertEquals(result.getEmailAddress(),authenticatedUser.getEmailAddress());
        }


    }

    @Nested
    class Scenario_TheEmailAddressIsIncorrect{

        @Test
        void shouldThrowNotFound(){

            var command= new LoginCommand("contact0@anvryacademy.fr","password");
            var commandHandler = createHandler();

            Assertions.assertThrows(NotFoundException.class,()->commandHandler.handle(command));
        }
    }


    @Nested
    class Scenario_ThePasswordIsIncorrect{

        @Test
        void shouldThrowNotFound(){

            var command= new LoginCommand("contact@anvryacademy.fr","notcorre");
            var commandHandler = createHandler();

            var exception= Assertions.assertThrows(BadRequestException.class,()->commandHandler.handle(command));
            Assertions.assertEquals("Invalid Password",exception.getMessage());
        }
    }
}
