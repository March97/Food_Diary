package com.example.food_diary.ui.biodata;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "biodata_table")
public class Biodata {

    @PrimaryKey(autoGenerate = true)
    private Integer id;
    private String date;
    private Integer mass;
    private Integer height;
    private Double bmi;
    private String email;

    public Biodata(Integer id, String date, Integer mass, Integer height, String email) {
        this.id = id;
        this.date = date;
        this.mass = mass;
        this.height = height;
        this.email = email;
        this.bmi = Double.valueOf(mass / (height * height) / 10000);
    }
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Integer getMass() {
        return mass;
    }

    public void setMass(Integer mass) {
        this.mass = mass;
    }

    public Integer getHeight() {
        return height;
    }

    public void setHeight(Integer height) {
        this.height = height;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getBmi() {
        return bmi;
    }

    public void setBmi(Double bmi) {
        this.bmi = bmi;
    }
}
