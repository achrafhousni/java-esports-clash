package com.ancyracademy.esportsclash.auth.application.services.jwtservice;

import com.ancyracademy.esportsclash.auth.domain.model.AuthUser;
import com.ancyracademy.esportsclash.auth.domain.model.User;

public interface JwtService {

    String tokenize(User user);

    AuthUser parse(String token);
}
