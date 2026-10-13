package com.ancyracademy.esportsclash.schedule.domain.model;

import com.ancyracademy.esportsclash.core.domain.model.BaseEntity;

public class Match extends BaseEntity<Match> {


    private String firstTeam;

    private String secondTeam;

    public Match(String id, String firstTeam, String secondTeam) {
        super(id);
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
     }

     public boolean hasTeam(String id){
        return firstTeam.equals(id) || secondTeam.equals(id);
     }
    @Override
    public Match deepClone() {
        return new Match(getId(),firstTeam,secondTeam);
    }
}
