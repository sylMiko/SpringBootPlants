package com.plants.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Plant {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private int id;
    private String name, plantingDate, floweringTime, maturityTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlantingDate() {
        return plantingDate;
    }

    public void setPlantingDate(String plantingDate) {
        this.plantingDate = plantingDate;
    }

    public String getFloweringTime() {
        return floweringTime;
    }

    public void setFloweringTime(String floweringTime) {
        this.floweringTime = floweringTime;
    }

    public String getMaturityTime() {
        return maturityTime;
    }

    public void setMaturityTime(String maturityTime) {
        this.maturityTime = maturityTime;
    }

    @Override
    public String toString() {
        return "Plant{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", plantingDate='" + plantingDate + '\'' +
                ", floweringTime='" + floweringTime + '\'' +
                ", maturityTime='" + maturityTime + '\'' +
                '}';
    }
}
