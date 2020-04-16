package com.example.food_diary.ui.biodata;

public class Biodata {

    private String date;
    private Integer mass;
    private Integer height;
    private String email;

    public Biodata(String date, Integer mass, Integer height, String email) {
        this.date = date;
        this.mass = mass;
        this.height = height;
        this.email = email;
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
