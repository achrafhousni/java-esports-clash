package com.ancyracademy.esportsclash.auth.application.ports;

import com.ancyracademy.esportsclash.auth.domain.model.AuthUser;

import java.util.Optional;

public interface  AuthContext {

    boolean isAuthenticated();

    Optional<AuthUser> getUser();
}
