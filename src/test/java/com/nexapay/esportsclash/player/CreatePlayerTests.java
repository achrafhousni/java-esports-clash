package com.nexapay.esportsclash.player;

import com.nexapay.esportsclash.player.application.usecases.CreatePlayerCommand;
import com.nexapay.esportsclash.player.application.usecases.CreatePlayerCommandHandler;
import com.nexapay.esportsclash.player.domain.model.Player;
import com.nexapay.esportsclash.player.infrastructure.spring.persistence.ram.InMemoryPlayerRepository;
import org.junit.Assert;
import org.junit.Test;

public class CreatePlayerTests {

    @Test
    public void ShouldCreatePlayer(){
        var repository= new InMemoryPlayerRepository();
        var useCase=new CreatePlayerCommandHandler(repository);
        var command=new CreatePlayerCommand("name");

        var result= useCase.handle(command);

         var expectedPlayer=new Player(result.getId(),"name");
         Player  actualPlayer=repository.findById(expectedPlayer.getId()).get();

         Assert.assertEquals(expectedPlayer.getName(),actualPlayer.getName());

    }
}
