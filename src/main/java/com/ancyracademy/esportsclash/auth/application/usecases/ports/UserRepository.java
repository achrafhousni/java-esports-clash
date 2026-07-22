package com.ancyracademy.esportsclash.auth.application.usecases.ports;

import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.core.infrastructure.persistence.BaseRepository;

import java.util.Optional;

public interface UserRepository extends BaseRepository<User> {
  //Optional<User> findByEmailAddress(String emailAddress);
  boolean isEmailAddressAvailable(String emailAddress);

    void clear();
}
