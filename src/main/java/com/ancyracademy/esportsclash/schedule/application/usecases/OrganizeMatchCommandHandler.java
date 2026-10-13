package com.ancyracademy.esportsclash.schedule.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;

import java.util.UUID;

public class OrganizeMatchCommandHandler implements Command.Handler<OrganizeMatchCommand, IdResponse> {


    private final TeamRepository teamRepository;
    private final ScheduleDayRepository scheduleDayRepository;

    public OrganizeMatchCommandHandler(TeamRepository teamRepository, ScheduleDayRepository scheduleDayRepository) {
        this.teamRepository = teamRepository;
        this.scheduleDayRepository = scheduleDayRepository;
    }

    @Override
    public IdResponse handle(OrganizeMatchCommand command) {

        var t1 = teamRepository.findById(command.getFirstTeamId()).orElseThrow(
                () -> new NotFoundException("Team",command.getFirstTeamId())
        );
        var t2 = teamRepository.findById(command.getSecondTeamId()).orElseThrow(
                () -> new NotFoundException("Team",command.getSecondTeamId())
        );

        var scheduleDay = scheduleDayRepository.findByDate(command.getDate()).orElse(
                new ScheduleDay(UUID.randomUUID().toString(),command.getDate()));

        var organizedMatch= scheduleDay.organize(t1, t2, command.getMoment());
        scheduleDayRepository.save(scheduleDay);

        return new IdResponse(organizedMatch.getId());
    }
}



