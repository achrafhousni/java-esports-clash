package com.ancyracademy.esportsclash.team.infrastructure.spring.controller;

import an.awesome.pipelinr.Pipeline;
import com.ancyracademy.esportsclash.player.application.usecases.CreatePlayerCommand;
import com.ancyracademy.esportsclash.player.application.usecases.DeletePlayerCommand;
import com.ancyracademy.esportsclash.player.application.usecases.GetPlayerByIdCommand;
import com.ancyracademy.esportsclash.player.application.usecases.RenamePlayerCommand;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.PlayerViewModel;
import com.ancyracademy.esportsclash.player.infrastructure.spring.CreatePlayerDTO;
import com.ancyracademy.esportsclash.player.infrastructure.spring.RenamePlayerDTO;
import com.ancyracademy.esportsclash.team.domain.model.application.usecases.CreateTeamCommand;
import com.ancyracademy.esportsclash.team.infrastructure.spring.dto.CreateTeamDTO;
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
}
