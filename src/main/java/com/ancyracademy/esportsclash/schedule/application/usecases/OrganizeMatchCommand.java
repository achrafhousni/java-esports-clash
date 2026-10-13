package com.ancyracademy.esportsclash.schedule.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.schedule.domain.model.Moment;

import java.time.LocalDate;


public class OrganizeMatchCommand implements Command<IdResponse> {
    private String firstTeamId;
    private String secondTeamId;
    private Moment moment;
    private LocalDate date;


    public OrganizeMatchCommand(String firstTeamId, String secondTeamId, Moment moment, LocalDate date) {
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.moment = moment;
        this.date = date;
    }

     public String getFirstTeamId() {
        return firstTeamId;
    }

    public String getSecondTeamId() {
        return secondTeamId;
    }

    public Moment getMoment() {
        return moment;
    }

    public LocalDate getDate() {
        return date;
    }


}
