package com.ancyracademy.esportsclash.auth.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import com.ancyracademy.esportsclash.auth.application.usecases.ports.UserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.User;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;

import java.util.UUID;

public class RegisterCommandHandler implements Command.Handler<RegisterCommand, IdResponse> {
 private final UserRepository userRepository;
   private final PasswordHasher passwordHasher;

    public RegisterCommandHandler(UserRepository userRepository, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.passwordHasher = passwordHasher;
    }


    @Override
    public IdResponse handle(RegisterCommand registerCommand) {
        var isEmailAddressAvailable = userRepository.isEmailAddressAvailable(registerCommand.getEmailAddress());
      if(!isEmailAddressAvailable){
        throw new IllegalArgumentException("Email address is already in use");
      }

        var user= new User(UUID.randomUUID().toString(),
        //registerCommand.getEmailAddress(),registerCommand.getPassword());
               registerCommand.getEmailAddress(),  passwordHasher.hash(registerCommand.getPassword()));
        userRepository.save(user);
        return new IdResponse(user.getId());
    }
}
