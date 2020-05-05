package com.example.food_diary.ui.meals;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MealRemoteDatabase {
    FirebaseAuth mAuth;

    public MealRemoteDatabase(){
        mAuth = FirebaseAuth.getInstance();

    }

    public FirebaseAuth  getMAuth(){
        return mAuth;
    }
}
