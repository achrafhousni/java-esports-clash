package com.ancyracademy.esportsclash.auth.infrastructure.persistence.jpa;

import com.ancyracademy.esportsclash.auth.application.ports.UserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.sql.SQLBaseRepository;
import jakarta.persistence.EntityManager;

import java.util.Optional;

public class SQLUserRepository extends SQLBaseRepository<User> implements UserRepository {

    public SQLUserRepository(EntityManager entityManager,SQLUserAccessor sqlUserAccessor){
        super(entityManager);
        this.sqlUserAccessor = sqlUserAccessor;
    }

    private SQLUserAccessor sqlUserAccessor;

    @Override
    public Class<User> getEntityClass() {
        return  User.class;
    }

    @Override
    public boolean isEmailAddressAvailable(String emailAddress) {
        return !sqlUserAccessor.existsByEmailAddress(emailAddress);
    }

    @Override
    public Optional<User> findByEmailAddress(String emailAddress) {
        return  Optional.ofNullable(sqlUserAccessor.findByEmailAddress(emailAddress));
    }
}
