package com.nexapay.esportsclash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.nexapay.esportsclash.core.domain.exceptions.NotFoundException;
import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.nexapay.esportsclash.player.domain.model.viewmodel.PlayerViewModel;

public class GetPlayerByIdCommandHandler implements Command.Handler<GetPlayerByIdCommand, PlayerViewModel> {
    private final PlayerRepository playerRepository;

    public GetPlayerByIdCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public PlayerViewModel handle(GetPlayerByIdCommand command) {
       var player= playerRepository.findById(command.getId()).orElseThrow(
               ()->new NotFoundException("player", command.getId())
       );
        return new PlayerViewModel(player.getId(),player.getName());
    }
}
