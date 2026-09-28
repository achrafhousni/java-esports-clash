package com.ancyracademy.esportsclash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.auth.application.ports.AuthContext;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;

public class DeletePlayerCommandHandler implements Command.Handler<DeletePlayerCommand,Void>{

    private final PlayerRepository playerRepository;
   // private final AuthContext auth;

    public DeletePlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
        //this.auth = auth;
    }

    @Override
    public Void handle(DeletePlayerCommand command) {
        var player= playerRepository.findById(command.getId()).orElseThrow(
               // ()->new NotFoundException("Player not found",command.getId())
                ()->new NotFoundException("Player",command.getId())
        );
        playerRepository.delete(player);
        return null;
    }
}
