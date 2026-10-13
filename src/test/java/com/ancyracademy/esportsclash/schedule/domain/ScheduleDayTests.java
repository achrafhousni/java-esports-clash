package com.ancyracademy.esportsclash.schedule.domain;

import com.ancyracademy.esportsclash.schedule.domain.model.Moment;
import com.ancyracademy.esportsclash.schedule.domain.model.ScheduleDay;
import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

public class ScheduleDayTests {
    Team createTeam(String id){

        var name= "Team "+id;
        var team =new Team(id,name);

        team.addMember(id+"-1", Role.TOP);
        team.addMember(id+"-2", Role.JUNGLE);
        team.addMember(id+"-3", Role.MIDDLE);
        team.addMember(id+"-4", Role.BOTTOM);
        team.addMember(id+"-5", Role.SUPPORT);
        return team;
    }

    Team createIncompleteTeam(String id){
        var name= "Team "+id;
        var team =new Team(id,name);

        team.addMember(id+"-1", Role.TOP);
        team.addMember(id+"-2", Role.JUNGLE);
        team.addMember(id+"-3", Role.MIDDLE);
        team.addMember(id+"-4", Role.BOTTOM);
         return team;
    }

   @Nested
   class OrganizeTests{
       @Test
       public void shouldOrganize(){
           Team team1 = createTeam("t1");
           Team team2 = createTeam("t2");
           var moment = Moment.MORNING;

           var scheduleDay = new ScheduleDay("1", LocalDate.now());
           scheduleDay.organize(team1, team2, moment);

           var match=scheduleDay.getAt(Moment.MORNING);
           Assertions.assertTrue(match.isPresent());
       }


       @Test
       public void whenMomentIsUnAvailable_shouldThrow(){
           Team team1 = createTeam("t1");
           Team team2 = createTeam("t2");
           Team team3 = createTeam("t3");
           Team team4 = createTeam("t4");


           var scheduleDay = new ScheduleDay("1", LocalDate.now());
           scheduleDay.organize(team1, team2, Moment.MORNING);

           var exception  = Assertions.assertThrows(IllegalArgumentException.class,
                   ()->scheduleDay.organize(team3, team4, Moment.MORNING));
           Assertions.assertEquals("Moment MORNING is already taken",exception.getMessage());
       }


       @Test
       public void whenTeamAlreadyPlyInTheMorning_shouldThrow(){
           Team team1 = createTeam("t1");
           Team team2 = createTeam("t2");
           Team team3 = createTeam("t3");


           var scheduleDay = new ScheduleDay("1", LocalDate.now());
           scheduleDay.organize(team1, team2, Moment.MORNING);

           var exception= Assertions.assertThrows(IllegalArgumentException.class,
                   ()->scheduleDay.organize(team1, team3, Moment.AFTERNOON));

           Assertions.assertEquals("One of the teams already played in the morning",exception.getMessage());
       }


       //optionel
       @Test
       public void whenTeamAlreadyPlyInThAfterNoon_shouldThrow(){
           Team team1 = createTeam("t1");
           Team team2 = createTeam("t2");
           Team team3 = createTeam("t3");


           var scheduleDay = new ScheduleDay("1", LocalDate.now());
           scheduleDay.organize(team1, team2, Moment.AFTERNOON);

           var exception= Assertions.assertThrows(IllegalArgumentException.class,
                   ()->scheduleDay.organize(team1, team3, Moment.MORNING));

           Assertions.assertEquals("One of the teams already played in the morning",exception.getMessage());
       }


       @Test
       public void whenFirstTeamIsInComplete_shouldFails(){
           var t1= createIncompleteTeam("t1");
           var t2= createTeam("t2");
           var scheduleDay = new ScheduleDay("1", LocalDate.now());
           var exception= Assertions.assertThrows(IllegalArgumentException.class,
                   ()->scheduleDay.organize(t1, t2, Moment.MORNING));
           Assertions.assertEquals("One of the teams is incomplete",exception.getMessage());
       }

       @Test
       public void whenSecondTeamIsInComplete_shouldFails(){
           var t1= createTeam("t1");
           var t2= createIncompleteTeam("t2");
           var scheduleDay = new ScheduleDay("1", LocalDate.now());
           var exception= Assertions.assertThrows(IllegalArgumentException.class,
                   ()->scheduleDay.organize(t1, t2, Moment.MORNING));
           Assertions.assertEquals("One of the teams is incomplete",exception.getMessage());
       }

   }

   @Nested
   class CancelTests{


        @Test
        void shouldCancel(){
            var t1= createTeam("t1");
            var t2= createTeam("t2");
            var scheduleDay = new ScheduleDay("1", LocalDate.now());
            var orginazedMatch=scheduleDay.organize(t1, t2, Moment.MORNING);
            scheduleDay.cancel(orginazedMatch.getId());

            var match = scheduleDay.getAt(Moment.MORNING);
            Assertions.assertTrue(match.isEmpty());
          }
   }
}
