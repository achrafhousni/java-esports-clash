package com.ancyracademy.esportsclash.schedule.infrastructure.spring.dto;

import com.ancyracademy.esportsclash.schedule.domain.model.Moment;

import java.time.LocalDate;

public class OrganizeMatchDTO {
    
    private String firstTeamId;
    private String secondTeamId;
    private String date;
    private String moment;

    public OrganizeMatchDTO() {
    }

    public OrganizeMatchDTO(String firstTeamId, String secondTeamId, String date, String moment) {
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.date = date;
        this.moment = moment;
    }

    public String getFirstTeamId() {
        return firstTeamId;
    }

    public String getSecondTeamId() {
        return secondTeamId;
    }

    public String getDate() {
        return date;
    }

    public Moment getMoment() {
        return Moment.fromString(moment);
    }

    public LocalDate toLocalDate(){
        return LocalDate.parse(date);
    }

}
