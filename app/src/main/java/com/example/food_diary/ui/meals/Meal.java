package com.example.food_diary.ui.meals;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "meal_table")
public class Meal {

    @PrimaryKey (autoGenerate = true)
    private int id;
    private String email;
    private String date;
    private String name;
    private String kind;
    private double mass;
    private int energy;
    private double carbs;
    private double protein;
    private double fat;
    private double portions;

    public Meal(String email, String date, String name, String kind, double mass, int energy, double carbs, double protein, double fat, double portions) {
        this.email = email;
        this.date = date;
        this.name = name;
        this.kind = kind;
        this.mass = mass;
        this.energy = energy;
        this.carbs = carbs;
        this.protein = protein;
        this.fat = fat;
        this.portions = portions;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public int getEnergy() {
        return energy;
    }

    public void setEnergy(int energy) {
        this.energy = energy;
    }

    public double getCarbs() {
        return carbs;
    }

    public void setCarbs(int carbs) {
        this.carbs = carbs;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(int protein) {
        this.protein = protein;
    }

    public double getFat() {
        return fat;
    }

    public void setFat(int fat) {
        this.fat = fat;
    }

    public double getPortions() {
        return portions;
    }

    public void setPortions(int portions) {
        this.portions = portions;
    }
}
