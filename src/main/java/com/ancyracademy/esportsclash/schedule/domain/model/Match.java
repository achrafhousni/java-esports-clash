package com.ancyracademy.esportsclash.schedule.domain.model;

import com.ancyracademy.esportsclash.core.domain.model.BaseEntity;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import jakarta.persistence.*;

@Entity
@Table(name="matches")
public class Match extends BaseEntity<Match> {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="first_team", insertable = false,
            updatable = false)
    //@MapsId("firstTeam")
    private Team first;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="second_team",insertable = false,
            updatable = false)
    //@MapsId("secondTeam")
    private Team second;

    @Column(name="first_team")
    private String firstTeam;

    @Column(name="second_team")
    private String secondTeam;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "schedule_day_id",insertable = false,
            updatable = false)
    //@MapsId("sycheduleDay")
    private ScheduleDay scheduleDay;

    @Column(name = "schedule_day_id")
    private String scheduleDayId;

    public Match(String id, String scheduleDayId,String firstTeam, String secondTeam) {
        super(id);
        this.scheduleDayId = scheduleDayId;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public Match() {

    }

    public boolean hasTeam(String id){
        return firstTeam.equals(id) || secondTeam.equals(id);
     }
    @Override
    public Match deepClone() {
        return new Match(getId(),scheduleDayId,firstTeam,secondTeam);
    }
}
