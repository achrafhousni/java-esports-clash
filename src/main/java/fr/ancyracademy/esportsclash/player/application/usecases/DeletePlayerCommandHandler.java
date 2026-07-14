package fr.ancyracademy.esportsclash.player.application.usecases;

import an.awesome.pipelinr.Command;
import fr.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import fr.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;

public class DeletePlayerCommandHandler implements Command.Handler<DeletePlayerCommand,Void>{

    private final PlayerRepository playerRepository;

    public DeletePlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
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
