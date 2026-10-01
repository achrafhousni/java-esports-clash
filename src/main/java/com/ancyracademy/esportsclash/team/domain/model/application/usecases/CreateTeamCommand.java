package com.ancyracademy.esportsclash.team.domain.model.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;

public class CreateTeamCommand implements Command<IdResponse> {

 private final String   name;

 public CreateTeamCommand(String name){
     this.name = name;
 }

 public String getName(){
     return this.name;
 }
}
