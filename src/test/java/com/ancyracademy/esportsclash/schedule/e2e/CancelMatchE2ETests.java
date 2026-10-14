package com.ancyracademy.esportsclash.schedule.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
import com.ancyracademy.esportsclash.schedule.domain.model.Moment;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import com.ancyracademy.esportsclash.schedule.infrastructure.spring.dto.CancelMatchDTO;
import com.ancyracademy.esportsclash.schedule.infrastructure.spring.dto.OrganizeMatchDTO;
import com.ancyracademy.esportsclash.team.application.ports.TeamRepository;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.util.UUID;

public class CancelMatchE2ETests extends IntegrationTests {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private ScheduleDayRepository scheduleDayRepository;

    Team team =new Team("123","Team");

    private Team createTeam(String id){

        var name= "Team "+id;
        var team =new Team(id,name);


        for(Role role: Role.values()){
            var player = new Player(UUID.randomUUID().toString(),"Player");
            playerRepository.save(player);
            team.addMember(player.getId(),role);
        }
        teamRepository.save(team);
        return team;
    }
    @BeforeEach
    public void setUp(){
        teamRepository.clear();
        teamRepository.save(team);
    }
    @Test
    public void shouldOrganizeMatch() throws Exception{

        var t1= createTeam("t1");
        var t2= createTeam("t2");

        var scheduleDay = new ScheduleDay("1",LocalDate.parse("2026-01-01"));

        var match= scheduleDay.organize(t1,t2, Moment.MORNING);
        scheduleDayRepository.save(scheduleDay);

        var dto=new CancelMatchDTO(match.getId());
        mockmvc.perform(MockMvcRequestBuilders
                        .post("/schedules/cancel-match")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization",createJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        var scheduleDayQuery = scheduleDayRepository.findById(scheduleDay.getId());
        Assertions.assertFalse(scheduleDayQuery.isPresent());



    }
}
