package me.fjbt.workshop.supes;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Random;

@Entity
public class Villain extends PanacheEntity {

    public String name;
    public String otherName;
    public int level;
    public String picture;

    @Column(columnDefinition = "TEXT")
    public String powers;

    public static Villain findRandom() {
        long countVillains = count();
        Random random = new Random();
        int randomVillain = random.nextInt((int) countVillains);
        return findAll().page(randomVillain, 1).firstResult();
    }

    @Override
    public String toString() {
        return "Villain{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", otherName='" + otherName + '\'' +
                ", level=" + level +
                ", picture='" + picture + '\'' +
                ", powers='" + powers + '\'' +
                '}';
    }

    public Villain (String name, String otherName, int level, String picture){
        this.name = name;
        this.otherName = otherName;
        this.level = level;
        this.picture = picture;
    }

    public Villain() {}
}
