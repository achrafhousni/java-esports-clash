package com.ancyracademy.esportsclash.auth;

import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.BcryptPasswordHasher;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class PasswordHasherTests {

    public PasswordHasher createHasher(){
        return new BcryptPasswordHasher();
    }

    @Test
    public void shouldMatchHashedPassword(){
        PasswordHasher hasher = createHasher();
        var clearPassword = "password";
        var hashedPassword = hasher.hash(clearPassword);
        var match = hasher.match(clearPassword, hashedPassword);

        Assertions.assertTrue(match);
    }
}
