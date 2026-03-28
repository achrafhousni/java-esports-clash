package fr.ancyracademy.esportsclash.player.infrastructure.spring;

import an.awesome.pipelinr.Pipeline;
import fr.ancyracademy.esportsclash.player.application.usecases.CreatePlayerCommand;
import fr.ancyracademy.esportsclash.player.application.usecases.RenamePlayerCommand;
import fr.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/players")
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

}
