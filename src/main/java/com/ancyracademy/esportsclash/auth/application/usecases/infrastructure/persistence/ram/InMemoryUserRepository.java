package com.ancyracademy.esportsclash.auth.application.usecases.infrastructure.persistence.ram;

import com.ancyracademy.esportsclash.auth.application.usecases.ports.UserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.ram.InMemoryBaseRepository;

public class InMemoryUserRepository extends InMemoryBaseRepository<User> implements UserRepository {


    @Override
    public boolean isEmailAddressAvailable(String emailAddress) {
        return entities.values().stream().noneMatch(user -> user.getEmailAddress().equals(emailAddress));
    }
}
