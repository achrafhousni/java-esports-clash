package com.ancyracademy.esportsclash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;

public class RenamePlayerCommandHandler implements Command.Handler<RenamePlayerCommand,Void> {
    private final PlayerRepository playerRepository;

    public RenamePlayerCommandHandler(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }


    @Override
    public Void handle(RenamePlayerCommand renamePlayerCommand) {
        var player=playerRepository.findById(renamePlayerCommand.getId()).orElseThrow(
                ()->{
                    throw new NotFoundException("Player",renamePlayerCommand.getId());
                }
        );
       /* if(playerQuery.isEmpty()){
            throw new NotFoundException("Player",renamePlayerCommand.getId());
        }*/
        //var player=playerQuery.get();
        player.rename(renamePlayerCommand.getName());
        playerRepository.save(player);
        return null;
    }
}
