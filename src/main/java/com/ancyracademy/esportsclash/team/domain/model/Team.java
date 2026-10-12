package com.ancyracademy.esportsclash.team.domain.model;

import com.ancyracademy.esportsclash.core.domain.model.BaseEntity;
import com.ancyracademy.esportsclash.player.domain.model.Player;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@Entity
@Table(name = "teams")
public class Team extends BaseEntity<Team> {

    private String name;
    @OneToMany(mappedBy = "team", cascade = CascadeType.ALL, orphanRemoval = true,fetch = FetchType.EAGER)
    private Set<TeamMember> members;

    public Team(String id, String name){
        super(id);
        this.name = name;
        this.members = new HashSet<>();
     }


     private  Team(  String id, String name,Set<TeamMember> members ){

         super(id);
         this.name = name;
         this.members = members;
     };

    public Team() {

    }

    public void addMember( String player, Role role){
        if(this.members.stream().anyMatch(member -> member.playerId.equals(player)))
            throw new IllegalArgumentException("Player already in team");

        if(this.members.stream().anyMatch(member -> member.role.equals(role)))
            throw new IllegalArgumentException("Role already taken");

        var member = new TeamMember(UUID.randomUUID().toString(), player, this.id,role);
        this.members.add(member);
    }

    public void removeMember(String player){
        if(this.members.stream().noneMatch(member -> member.playerId.equals(player)))
            throw new IllegalArgumentException("Player not in team");
        this.members.removeIf(member -> member.playerId.equals(player));
    }

     public boolean hasMember(String player,Role role){
        return this.members.stream()
                .anyMatch(member -> member.playerId.equals(player) && member.role.equals(role));
    }

   public String getName(){
        return this.name;
    }

    @Override
    public Team deepClone() {
        return new Team(this.id,this.name,this.members.stream().map(TeamMember::deepClone).collect(Collectors.toSet()));
    }


    public Set<TeamMember> getMembers(){
        return this.members;
    }
    @Entity
    @Table(name = "team_members")
     public  static class TeamMember extends BaseEntity<TeamMember>{
        @Column(name = "player_id")
        private String playerId;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "player_id",insertable = false,updatable = false)
        @MapsId("playerId")
        private Player player;

        @Column(name = "team_id")
        private String teamId;

        @Column(name = "role")
        @Enumerated(EnumType.STRING)
        private Role role;


        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "team_id",insertable = false,updatable = false)
        @MapsId("teamId")
        private Team team;

       public  TeamMember(){

        }
        public TeamMember(String id, String playerId,String teamId , Role role){
           super(id);
            this.playerId = playerId;
            this.teamId = teamId;
            this.role = role;
        }



        @Override
        public TeamMember deepClone() {
         return new TeamMember(this.id, this.playerId, this.teamId, this.role);
        }

        public Player getPlayer(){
            return this.player;
        }

        public Role getRole(){
            return this.role;
        }
    }
}
