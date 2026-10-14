package com.ancyracademy.esportsclash.schedule.e2e;

import com.ancyracademy.esportsclash.IntegrationTests;
import com.ancyracademy.esportsclash.player.application.usecases.ports.PlayerRepository;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;
import com.ancyracademy.esportsclash.schedule.application.ports.ScheduleDayRepository;
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

import java.util.UUID;

public class OrganizeMatchE2ETests extends IntegrationTests {

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
        var dto =new OrganizeMatchDTO(t1.getId(),t2.getId(),"2026-01-01","MORNING");
        var result=mockmvc.perform(MockMvcRequestBuilders
                        .post("/schedules/organize-match")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(dto))
                        .header("Authorization",createJWT()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
     var idResponse= objectMapper.readValue(result.getResponse().getContentAsString(), IdResponse.class);

     var scheduleDayQuery = scheduleDayRepository.findByDate(dto.toLocalDate());
     Assertions.assertTrue(scheduleDayQuery.isPresent());
     var scheduleDay = scheduleDayQuery.get();
     var mathQuery= scheduleDay.getAt(dto.getMoment());
     Assertions.assertTrue(mathQuery.isPresent());
     var match = mathQuery.get();

     Assertions.assertEquals(idResponse.getId(),match.getId());
     Assertions.assertTrue(match.hasTeam(t1.getId()));
     Assertions.assertTrue(match.hasTeam(t2.getId()));



    }

}
