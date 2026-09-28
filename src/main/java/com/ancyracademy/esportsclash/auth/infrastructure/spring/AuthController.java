package com.ancyracademy.esportsclash.auth.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import com.ancyracademy.esportsclash.auth.application.usecases.LoginCommand;
import com.ancyracademy.esportsclash.auth.application.usecases.RegisterCommand;
import com.ancyracademy.esportsclash.auth.domain.model.LoggedInUserViewModel;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Transactional
public class AuthController {

    private final Pipeline pipeline;

    public AuthController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping("/register")
    public ResponseEntity<IdResponse> register(@Valid @RequestBody RegisterDTO registerDTO) {

        return new ResponseEntity( pipeline
                .send(new RegisterCommand(
                        registerDTO.getEmailAddress(),
                        registerDTO.getPassword())), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<LoggedInUserViewModel> register(@Valid @RequestBody LoginDTO registerDTO) {

        return new ResponseEntity( pipeline
                .send(new LoginCommand(
                        registerDTO.getEmailAddress(),
                        registerDTO.getPassword())), HttpStatus.OK);
    }
}
