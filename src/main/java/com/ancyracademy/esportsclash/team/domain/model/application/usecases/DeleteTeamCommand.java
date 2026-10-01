package com.ancyracademy.esportsclash.team.domain.model.application.usecases;

import an.awesome.pipelinr.Command;
import com.ancyracademy.esportsclash.player.domain.model.viewmodel.IdResponse;

public class DeleteTeamCommand implements Command<IdResponse> {

 private final String id;

 public DeleteTeamCommand(String name){
     this.id = name;
 }

 public String getId(){
     return this.id;
 }
}
