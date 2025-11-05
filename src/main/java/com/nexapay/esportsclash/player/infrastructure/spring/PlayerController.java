package com.nexapay.esportsclash.player.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import com.nexapay.esportsclash.player.application.usecases.CreatePlayerCommand;
import com.nexapay.esportsclash.player.application.usecases.DeletePlayerCommand;
import com.nexapay.esportsclash.player.application.usecases.GetPlayerByIdCommand;
import com.nexapay.esportsclash.player.application.usecases.RenamePlayerCommand;
import com.nexapay.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.nexapay.esportsclash.player.domain.model.viewmodel.PlayerViewModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
@Transactional
public class PlayerController {
    //private final InMemoryPlayerRepository repository;
    //private final CreatePlayerUseCase createPlayerUseCase;
    private final Pipeline pipeline;

    public PlayerController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }


    @PostMapping
    public ResponseEntity<IdResponse> createPlayer(@RequestBody CreatePlayerDTO dto){
        //var useCase=new CreatePlayerUseCase(repository);
        var result=this.pipeline.send(new CreatePlayerCommand(dto.getName()));
        //var result=useCase.execute(dto.getName());
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }


    @PatchMapping("{id}/rename")
    public ResponseEntity<Void> changePlayerName(@RequestBody RenamePlayerDTO dto
            ,@PathVariable("id") String id){
        //var useCase=new CreatePlayerUseCase(repository);
        var result=this.pipeline.send(new RenamePlayerCommand(id,dto.getName()));
        //var result=useCase.execute(dto.getName());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlayer(
            @PathVariable("id") String id){
        //var useCase=new CreatePlayerUseCase(repository);
        var result=this.pipeline.send(new DeletePlayerCommand(id));
        //var result=useCase.execute(dto.getName());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PlayerViewModel> getPlayerById(
            @PathVariable("id") String id){

        var result= this.pipeline.send(new GetPlayerByIdCommand(id));
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

}
