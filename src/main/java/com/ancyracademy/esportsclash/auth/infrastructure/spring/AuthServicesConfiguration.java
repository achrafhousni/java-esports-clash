package com.ancyracademy.esportsclash.auth.infrastructure.spring;

import com.ancyracademy.esportsclash.auth.application.services.jwtservice.JwtService;
import com.ancyracademy.esportsclash.auth.application.services.jwtservice.ConcreteJwtService;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.BcryptPasswordHasher;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class AuthServicesConfiguration {

    @Bean
    public PasswordHasher passwordHasher(){

        return new BcryptPasswordHasher();

    }

    @Bean
    public JwtService jwtService(){
        return new ConcreteJwtService("your-secret-key-here-minimum-256-bits", 3600);
    }
}
