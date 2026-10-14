package com.ancyracademy.esportsclash.schedule.infrastructure.spring.controller;

import an.awesome.pipelinr.Pipeline;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.schedule.application.usecases.CancelMatchCommand;
import com.ancyracademy.esportsclash.schedule.application.usecases.OrganizeMatchCommand;
import com.ancyracademy.esportsclash.schedule.infrastructure.spring.dto.CancelMatchDTO;
import com.ancyracademy.esportsclash.schedule.infrastructure.spring.dto.OrganizeMatchDTO;
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
@RequestMapping("/schedules")
@Transactional
public class ScheduleDayController {

    private final Pipeline pipeline;

    public ScheduleDayController(Pipeline pipeline) {
        this.pipeline = pipeline;
    }



    @PostMapping("/organize-match")
    public ResponseEntity<IdResponse> organizeMatch(@RequestBody OrganizeMatchDTO dto){
    var result=this.pipeline.send(
            new OrganizeMatchCommand(
                    dto.getFirstTeamId(),
                    dto.getSecondTeamId(),
                    dto.getMoment(),
                    dto.toLocalDate()
            ));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }


    @PostMapping("/cancel-match")
    public ResponseEntity<Void> cancelMatch(@RequestBody CancelMatchDTO dto){
        var result=this.pipeline.send(
                new CancelMatchCommand(
                        dto.getMatchId()
                ));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}
