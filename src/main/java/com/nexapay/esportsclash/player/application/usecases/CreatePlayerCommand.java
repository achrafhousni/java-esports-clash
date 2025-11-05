package com.nexapay.esportsclash.player.application.usecases;

import an.awesome.pipelinr.Command;
import com.nexapay.esportsclash.player.domain.model.viewmodel.IdResponse;

public class CreatePlayerCommand implements Command<IdResponse> {

    private String name;

    public CreatePlayerCommand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
