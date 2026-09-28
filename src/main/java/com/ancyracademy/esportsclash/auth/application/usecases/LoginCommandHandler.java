package com.ancyracademy.esportsclash.auth.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.auth.application.services.jwtservice.JwtService;
import com.ancyracademy.esportsclash.auth.application.services.passwordhasher.PasswordHasher;
import com.ancyracademy.esportsclash.auth.application.ports.UserRepository;
import com.ancyracademy.esportsclash.auth.domain.model.LoggedInUserViewModel;
import com.ancyracademy.esportsclash.core.domain.exceptions.BadRequestException;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;

public class LoginCommandHandler implements Command.Handler<LoginCommand, LoggedInUserViewModel> {

    private final UserRepository userRepository;

    private final JwtService jwtService;

    private    PasswordHasher passwordHasher;

    public LoginCommandHandler(UserRepository userRepository, JwtService jwtService, PasswordHasher passwordHasher) {
        this.userRepository = userRepository;
        this.jwtService = jwtService;
        this.passwordHasher = passwordHasher;
    }

    @Override
    public LoggedInUserViewModel handle(LoginCommand loginCommand) {

        var user = this.userRepository.findByEmailAddress(loginCommand.getEmailAddress()).orElseThrow(()->new NotFoundException("User"));
        var match=this.passwordHasher.match(loginCommand.getPassword(),user.getPasswordHash());
            if(!match)
                throw new BadRequestException("Invalid Password");
         var token = this.jwtService.tokenize(user);
        return new LoggedInUserViewModel(user.getId(), user.getEmailAddress(),token);
    }
}
