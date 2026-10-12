package com.ancyracademy.esportsclash.team.infrastructure.spring.controller;

import an.awesome.pipelinr.Pipeline;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.team.application.usecases.*;
import com.ancyracademy.esportsclash.team.domain.model.viewmodel.TeamViewModel;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.AddPlayerToTeamDTO;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.CreateTeamDTO;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.RemovePlayerFromTeamDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
@Transactional
public class TeamController {

    private final Pipeline pipeline;

    public TeamController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }

    @PostMapping
    public ResponseEntity<IdResponse> createTeam(@RequestBody CreateTeamDTO dto){
         var result=this.pipeline.send(new CreateTeamCommand(dto.getName()));
         return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @PostMapping("/add-player-to-team")
    public ResponseEntity<Void> addPlayerToTeam(@RequestBody AddPlayerToTeamDTO dto){
         this.pipeline.send(new AddPlayerToTeamCommand(dto.getPlayerId(),dto.getTeamId(),dto.toRole()));
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("/remove-player-from-team")
    public ResponseEntity<Void> removePlayerFromTeam(@RequestBody RemovePlayerFromTeamDTO dto){
         this.pipeline.send(new RemovePlayerFromTeamCommand(dto.getPlayerId(),dto.getTeamId()));
         return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable String id){
        this.pipeline.send(new DeleteTeamCommand(id));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamViewModel> getTeamById(@PathVariable String id){
        var result=this.pipeline.send(new GetTeamByIdCommand(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
