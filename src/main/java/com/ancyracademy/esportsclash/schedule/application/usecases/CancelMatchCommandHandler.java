package com.ancyracademy.esportsclash.schedule.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;

public class CancelMatchCommandHandler  implements Command.Handler<CancelMatchCommand, Void>{

    private ScheduleDayRepository scheduleDayRepository;

    public CancelMatchCommandHandler(ScheduleDayRepository scheduleDayRepository){
        this.scheduleDayRepository = scheduleDayRepository;
    }

    @Override
    public Void handle(CancelMatchCommand command) {

        var scheduleDay = scheduleDayRepository.findByMatchId(command.getMatchId()).
                orElseThrow(()-> new NotFoundException("Match",command.getMatchId()));

        scheduleDay.cancel(command.getMatchId());

        if(scheduleDay.haMatches()){
            scheduleDayRepository.save(scheduleDay);

        } else{
            scheduleDayRepository.delete(scheduleDay);
        }

        return null;
    }
}
