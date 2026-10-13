package com.ancyracademy.esportsclash.schedule.usecases;

import com.ancyracademy.esportsclash.core.domain.exceptions.NotFoundException;
import com.ancyracademy.esportsclash.schedule.application.usecases.OrganizeMatchCommand;
import com.ancyracademy.esportsclash.schedule.application.usecases.OrganizeMatchCommandHandler;
import com.ancyracademy.esportsclash.schedule.domain.model.Moment;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import com.ancyracademy.esportsclash.schedule.infrastructure.persistence.ram.InMemoryScheduleDayRepository;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import com.ancyracademy.esportsclash.team.infrastructure.persistence.ram.InMemoryTeamRepository;
import org.junit.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class OrganizeMatchTests {

    private InMemoryScheduleDayRepository scheduleDayRepository = new InMemoryScheduleDayRepository();

    private TeamRepository teamRepository = new InMemoryTeamRepository();

    @BeforeEach
    public void setUp() {
        scheduleDayRepository.clear();
        teamRepository.clear();

     }

     OrganizeMatchCommandHandler  createHandler() {
         return new OrganizeMatchCommandHandler(teamRepository, scheduleDayRepository);
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
    void shouldOrganizeMatch(){
       var t1 = createTeam("t1");
       var t2 = createTeam("t2");
       teamRepository.save(t1);
       teamRepository.save(t2);

       var date=LocalDate.parse("2027-01-01");
       var command = new OrganizeMatchCommand(t1.getId(),t2.getId(), Moment.MORNING, date);
       var handler = createHandler();
       var response= handler.handle(command);

       var scheduleDay = scheduleDayRepository.findByDate(date);

       Assertions.assertTrue(scheduleDay.isPresent());
       var scheduleDayOptional = scheduleDay.get();

       var matchQuery =scheduleDayOptional.getAt(command.getMoment());
       Assertions.assertTrue(matchQuery.isPresent());
       var match = matchQuery.get();
       Assertions.assertEquals(response.getId(),match.getId());

       Assertions.assertTrue(match.hasTeam(t1.getId()));
       Assertions.assertTrue(match.hasTeam(t2.getId()));


    }


    @Test
    void whenFirstTeamDoesNotExist_shouldThrow(){
        var t1 = createTeam("t1");
         teamRepository.save(t1);

        var date=LocalDate.parse("2027-01-01");
        var command = new OrganizeMatchCommand(t1.getId(),"garbage", Moment.MORNING, date);
        var handler = createHandler();


        var exception= Assertions.assertThrows(NotFoundException.class, () -> handler.handle(command));
        Assertions.assertEquals("Team with the key garbage not found", exception.getMessage());

    }


    @Test
    void whenSecondTeamDoesNotExist_shouldThrow(){
        var t2 = createTeam("t2");
        teamRepository.save(t2);

        var date=LocalDate.parse("2027-01-01");
        var command = new OrganizeMatchCommand("garbage",t2.getId(), Moment.MORNING, date);
        var handler = createHandler();


        var exception= Assertions.assertThrows(NotFoundException.class, () -> handler.handle(command));
        Assertions.assertEquals("Team with the key garbage not found", exception.getMessage());

    }

    @Test
    void whenScheduleDayAlreadyExists_shouldReuse(){
        var t1 = createTeam("t1");
        var t2 = createTeam("t2");
        var date=LocalDate.parse("2027-01-01");

        teamRepository.save(t1);
        teamRepository.save(t2);

        var scheduleDay = new ScheduleDay("1",date);
        scheduleDayRepository.save(scheduleDay);
        var handler = createHandler();
        var command = new OrganizeMatchCommand(t1.getId(),t2.getId(), Moment.MORNING, date);

        var response = handler.handle(command);

        var updatedScheduleDay = scheduleDayRepository.findById(scheduleDay.getId()).get();
        var matchQuery =  updatedScheduleDay.getAt(Moment.MORNING);
        Assertions.assertTrue(matchQuery.isPresent());
        var match = matchQuery.get();
        Assertions.assertEquals(response.getId(),match.getId());

        Assertions.assertTrue(match.hasTeam(t1.getId()));
        Assertions.assertTrue(match.hasTeam(t2.getId()));

    }

}
