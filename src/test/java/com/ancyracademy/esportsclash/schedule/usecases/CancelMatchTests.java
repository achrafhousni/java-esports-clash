package com.ancyracademy.esportsclash.schedule.usecases;

import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.schedule.application.usecases.CancelMatchCommand;
import com.ancyracademy.esportsclash.schedule.application.usecases.CancelMatchCommandHandler;
import com.ancyracademy.esportsclash.schedule.domain.model.Moment;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import com.ancyracademy.esportsclash.schedule.infrastructure.persistence.ram.InMemoryScheduleDayRepository;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class CancelMatchTests {

    InMemoryScheduleDayRepository scheduleDayRepository = new InMemoryScheduleDayRepository();

    CancelMatchCommandHandler   createHandler(){
         return new CancelMatchCommandHandler(scheduleDayRepository);
     }

    private Team createTeam(String id){

        var name= "Team "+id;
        var team =new Team(id,name);

        team.addMember(id+"-1", Role.TOP);
        team.addMember(id+"-2", Role.JUNGLE);
        team.addMember(id+"-3", Role.MIDDLE);
        team.addMember(id+"-4", Role.BOTTOM);
        team.addMember(id+"-5", Role.SUPPORT);
        return team;
    }


    @Test
    void shouldCancelMatch(){

        var scheduleDay= new ScheduleDay("1", LocalDate.parse("2023-01-01"));

        var t1=createTeam("1");
        var t2=createTeam("2");
        var t3=createTeam("3");
        var t4=createTeam("4");
        var match=scheduleDay.organize(t1,t2, Moment.MORNING);
        scheduleDay.organize(t3,t4, Moment.AFTERNOON);
        scheduleDayRepository.save(scheduleDay);

        var command=new CancelMatchCommand(match.getId());
        createHandler().handle(command);

        var updatedScheduleDay=scheduleDayRepository.findById("1").get();
        Assertions.assertFalse(updatedScheduleDay.getAt(Moment.MORNING).isPresent());
      }

    @Test
    void whenMatchDoseNotExist_shouldThrow(){
        var command=new CancelMatchCommand("non-existent-match-id");
        var exception= Assertions.assertThrows(NotFoundException.class, () -> createHandler().handle(command));
        Assertions.assertEquals("Match with the key non-existent-match-id not found",exception.getMessage());
    }


    @Test
    void whenThereIsOnlyOneMatch_shouldDeleteTheScheduleDay(){

        var scheduleDay= new ScheduleDay("1", LocalDate.parse("2023-01-01"));

        var t1=createTeam("1");
        var t2=createTeam("2");

        var match=scheduleDay.organize(t1,t2, Moment.MORNING);
        scheduleDayRepository.save(scheduleDay);

        var command=new CancelMatchCommand(match.getId());
        createHandler().handle(command);

        var updatedScheduleDayQuery  =scheduleDayRepository.findById("1");
        Assertions.assertFalse(updatedScheduleDayQuery.isPresent());
    }
}
