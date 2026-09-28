package com.ancyracademy.esportsclash.auth;

import com.ancyracademy.esportsclash.auth.application.exceptions.EmailAddressUnavailableException;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.BcryptPasswordHasher;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import com.ancyracademy.esportsclash.auth.application.usecases.RegisterCommand;
import com.ancyracademy.esportsclash.auth.application.usecases.RegisterCommandHandler;
import com.ancyracademy.esportsclash.auth.infrastructure.persistence.ram.InMemoryUserRepository;
import com.ancyracademy.esportsclash.auth.application.ports.UserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class RegisterTests {


    private UserRepository repository = new InMemoryUserRepository();
    private PasswordHasher passwordHasher = new BcryptPasswordHasher();

    public RegisterCommandHandler createCommandHandler(){
        return new RegisterCommandHandler(repository, passwordHasher);
    }
        @BeforeEach
        public void setUp(){
            repository.clear();
        }


    @Test
    public void shouldRegister() {

        RegisterCommand command = new RegisterCommand("contact@ancry.fr","password");
        RegisterCommandHandler commandHandler=createCommandHandler();

        var response= commandHandler.handle(command);
        User actualUser= repository.findById(response.getId()).get();
        Assertions.assertEquals("contact@ancry.fr",actualUser.getEmailAddress());
        //Assert.assertEquals("password",actualUser.getPassword());
        Assertions.assertTrue(passwordHasher.match(command.getPassword(), actualUser.getPasswordHash()));
    }

    @Test
    public void whenEmailAddressInUse_shouldThrow() {
        var existingUser=new User("123","contact@ancry.fr",
                "password");
        repository.save(existingUser);
        RegisterCommand command = new RegisterCommand(existingUser.getEmailAddress(),
                "password");
        var  commandHandler= createCommandHandler();

        var exception = Assert.assertThrows(  EmailAddressUnavailableException.class, ()->commandHandler.handle(command));


    }
}
