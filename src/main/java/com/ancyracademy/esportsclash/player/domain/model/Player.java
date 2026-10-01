package com.ancyracademy.esportsclash.player.domain.model;

import com.ancyracademy.esportsclash.core.domain.model.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="players")
public class Player extends BaseEntity<Player> {

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

    @Override
    public Player deepClone(){
        return new Player(this.id,this.name);
    }

}
