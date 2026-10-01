package com.ancyracademy.esportsclash.team.domain.model;

import com.ancyracademy.esportsclash.core.domain.model.BaseEntity;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class Team extends BaseEntity<Team> {

    private String name;
    private Set<TeamMember> members;

    public Team(String id, String name){
        this.id = id;
        this.name = name;
        this.members = new HashSet<>();
     }

     private  Team(  String id, String name,Set<TeamMember> members ){

         this.id = id;
         this.name = name;
         this.members = members;
     };
    public void addMember( String player, Role role){
        if(this.members.stream().anyMatch(member -> member.player.equals(player)))
            throw new IllegalArgumentException("Player already in team");

        if(this.members.stream().anyMatch(member -> member.role.equals(role)))
            throw new IllegalArgumentException("Role already taken");

        var member = new TeamMember(UUID.randomUUID().toString(),player, role);
        this.members.add(member);
    }

    public void removeMember(String player){
        if(this.members.stream().noneMatch(member -> member.player.equals(player)))
            throw new IllegalArgumentException("Player not in team");
        this.members.removeIf(member -> member.player.equals(player));
    }

     public boolean hasMember(String player,Role role){
        return this.members.stream()
                .anyMatch(member -> member.player.equals(player) && member.role.equals(role));
    }

   public String getName(){
        return this.name;
    }

    @Override
    public Team deepClone() {
        return new Team(this.id,this.name,this.members.stream().map(TeamMember::deepClone).collect(Collectors.toSet()));
    }


    class TeamMember extends BaseEntity<TeamMember>{
        private String player;
        private Role role;

        public TeamMember(String id,String player, Role role){
                      super(id);
            this.player = player;
            this.role = role;
        }

        @Override
        public TeamMember deepClone() {
         return new TeamMember(this.id,this.player,this.role);
        }
    }
}
