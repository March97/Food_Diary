package com.example.food_diary.ui.biodata;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "biodata_table")
public class Biodata {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String date;
    private int mass;
    private int height;
    private String email;

    public Biodata(String date, int mass, int height, String email) {
        this.date = date;
        this.mass = mass;
        this.height = height;
        this.email = email;
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

}
