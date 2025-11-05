package com.nexapay.esportsclash.player.domain.model.viewmodel;

public class PlayerViewModel {
private   String id;

private    String name;

    public PlayerViewModel( ) {

    }

    public PlayerViewModel(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
