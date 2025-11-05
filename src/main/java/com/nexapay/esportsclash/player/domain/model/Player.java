package com.nexapay.esportsclash.player.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class Player extends BaseEntity {
    @Id
    private String id;
    @Column
    private String name;

    public Player() {

    }

    public Player(String id, String name){
        this.id=id;
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }

    public String rename(String name){
        this.name=name;
        return name;
    }
}
