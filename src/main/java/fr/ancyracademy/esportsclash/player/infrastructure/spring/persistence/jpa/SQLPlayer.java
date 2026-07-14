package fr.ancyracademy.esportsclash.player.infrastructure.spring.persistence.jpa;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// à suypprimer
@Entity
@Table(name="players")
public class SQLPlayer {
    @Id
    private String id;
    @Column
    private String name;

    public SQLPlayer() {

    }

    public SQLPlayer(String id, String name){
        this.id=id;
        this.name=name;
    }

    public String getName(){
        return name;
    }

    public String getId(){
        return id;
    }
}
