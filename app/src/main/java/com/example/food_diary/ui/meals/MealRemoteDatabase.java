package com.example.food_diary.ui.meals;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class MealRemoteDatabase {
    FirebaseDatabase database;
    DatabaseReference myRef;


    public void createInstance() {
        // Write a message to the database
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Q2orj3h0bzifU2uBOQ8j");

        myRef.setValue("Hello, World!");
    }

}
