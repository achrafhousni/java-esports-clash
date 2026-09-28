package com.ancyracademy.esportsclash.auth.infrastructure.spring;

import com.ancyracademy.esportsclash.auth.application.services.jwtservice.JwtService;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import com.ancyracademy.esportsclash.auth.application.usecases.LoginCommandHandler;
import com.ancyracademy.esportsclash.auth.application.usecases.RegisterCommandHandler;
import com.ancyracademy.esportsclash.auth.application.ports.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthUseCaseConfiguration {

    @Bean
    public RegisterCommandHandler registerCommandHandler(
            UserRepository userRepository,
            PasswordHasher passwordHasher
    ){
        return new RegisterCommandHandler(userRepository,passwordHasher);
    }

    @Bean
    public LoginCommandHandler loginCommandHandler(
            UserRepository userRepository,
            JwtService jwtService,
            PasswordHasher passwordHasher
    ){
        return new LoginCommandHandler(userRepository,jwtService,passwordHasher);
    }
}
