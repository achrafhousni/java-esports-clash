package com.ancyracademy.esportsclash.auth.infrastructure.spring;

import com.ancyracademy.esportsclash.auth.application.ports.AuthContext;
import com.ancyracademy.esportsclash.auth.infrastructure.persistence.jpa.SQLUserAccessor;
import com.ancyracademy.esportsclash.auth.infrastructure.persistence.jpa.SQLUserRepository;
import com.ancyracademy.esportsclash.auth.application.ports.UserRepository;
import jakarta.persistence.EntityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthAdapterConfiguration {

    @Bean
    public UserRepository getUserRepository(EntityManager entityManager, SQLUserAccessor sqlUserAccessor) {
        //return new InMemoryUserRepository();
        return new SQLUserRepository( entityManager,sqlUserAccessor);
    }

    @Bean
    public AuthContext getAuthContext() {
        return new SpringAuthContext();
    }

}

