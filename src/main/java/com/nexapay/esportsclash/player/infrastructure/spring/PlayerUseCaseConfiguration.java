package com.nexapay.esportsclash.player.infrastructure.spring;

import com.nexapay.esportsclash.player.application.usecases.DeletePlayerCommandHandler;
import com.nexapay.esportsclash.player.application.usecases.GetPlayerByIdCommandHandler;
import com.nexapay.esportsclash.player.application.usecases.RenamePlayerCommandHandler;
import com.nexapay.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.nexapay.esportsclash.player.application.usecases.CreatePlayerCommandHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PlayerUseCaseConfiguration {

    @Bean
    public CreatePlayerCommandHandler createPlayerUseCase(PlayerRepository repository){
        return new CreatePlayerCommandHandler(repository);
    }

    @Bean
    public RenamePlayerCommandHandler renamePlayerUseCase(PlayerRepository repository){
        return new RenamePlayerCommandHandler(repository);
    }

    @Bean
    public DeletePlayerCommandHandler  deletePlayerUseCase(PlayerRepository repository){
          return new DeletePlayerCommandHandler(repository);
    }

    @Bean
    public GetPlayerByIdCommandHandler getPlayerByIdUseCase(PlayerRepository repository){
        return new GetPlayerByIdCommandHandler(repository);
    }
}
