package com.ancyracademy.esportsclash.team.domain;

import com.ancyracademy.esportsclash.team.domain.model.Role;
import com.ancyracademy.esportsclash.team.domain.model.Team;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertTrue;

public class TeamTests {

    @Nested
    class AddMember{

    @Test
    void shouldJoinTeam(){
        var team = new Team("123","TeamName");
        team.addMember("Player1", Role.TOP);
        Assertions.assertTrue(team.hasMember("Player1", Role.TOP));
    }

    @Test
    void whenPlayerIsAlreadyInTeam_shouldThrowException(){
        var team = new Team("123","TeamName");
        team.addMember("Player1", Role.TOP);
        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.addMember("Player1", Role.BOTTOM));

        Assertions.assertEquals("Player already in team", exception.getMessage());
    }

    @Test
    void whenRoleIAlreadyTaken_shouldThrowExceptionWithCorrectMessage(){
        var team = new Team("123","TeamName");
        team.addMember("Player1", Role.TOP);
        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.addMember("Player2", Role.TOP));

        Assertions.assertEquals("Role already taken", exception.getMessage());
    }

    @Test
    void whenTeamIsNotComplete_shouldThrowException(){
        var team = new Team("123","TeamName 1");
        team.addMember("1", Role.TOP);
        team.addMember("2", Role.JUNGLE);
        team.addMember("3", Role.MIDDLE);
        team.addMember("4", Role.BOTTOM);

        Assertions.assertFalse(team.isComplete());
     }

        @Test
        void whenTeamHas5Members_shouldBeComplete(){
            var team = new Team("123","TeamName 1");
            team.addMember("1", Role.TOP);
            team.addMember("2", Role.JUNGLE);
            team.addMember("3", Role.MIDDLE);
            team.addMember("4", Role.BOTTOM);
            team.addMember("5", Role.SUPPORT);
            Assertions.assertTrue(team.isComplete());
        }

}

@Nested
class RemoveMember{

  @Test
  void shouldRemoveMember(){
    var team = new Team("123","TeamName");
    team.addMember("Player1", Role.TOP);
    team.removeMember("Player1");
    Assertions.assertFalse(team.hasMember("Player1", Role.TOP));
  }

    @Test
    void whenMemberIsNotInTeam_shouldThrowException(){
        var team = new Team("123","TeamName");
        team.addMember("Player1", Role.TOP);
        var exception = Assertions.assertThrows(IllegalArgumentException.class,
                () -> team.removeMember("Player2"));

        Assertions.assertEquals("Player not in team", exception.getMessage());
    }
}

}
