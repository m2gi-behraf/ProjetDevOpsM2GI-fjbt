package me.fjbt.workshop.supes;


import io.quarkus.hibernate.orm.panache.PanacheEntity;
import javax.persistence.Column;
import javax.persistence.Entity;

import java.util.Random;


@Entity
public class Hero extends PanacheEntity {

    public String name;
    public String otherName;
    public int level;
    public String picture;

    @Column(columnDefinition = "TEXT")
    public String powers;

    @Override
    public String toString() {
        return "Hero{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", otherName='" + otherName + '\'' +
                ", level=" + level +
                ", picture='" + picture + '\'' +
                ", powers='" + powers + '\'' +
                '}';
    }

    public Hero (String name, String otherName, int level, String picture){
        this.name = name;
        this.otherName = otherName;
        this.level = level;
        this.picture = picture;
    }
}
