package com.example.food_diary.ui.meals;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.food_diary.ui.biodata.Biodata;

import java.util.List;

@Dao
public interface MealDao {

    @Insert
    void insert(Meal meal);

    @Update
    void update(Meal meal);

    @Delete
    void delete(Meal meal);

    @Query("DELETE FROM meal_table")
    void deleteAll();

    //void get(Biodata biodata);

    @Query("SELECT * FROM meal_table ORDER BY id DESC")
    LiveData<List<Meal>> getAll();

//    @Query("SELECT * FROM meal_table WHERE kind = 'Breakfast' AND date LIKE :date ORDER BY id DESC")
//    LiveData<List<Meal>> getBreakfast(String date);
//
//    @Query("SELECT * FROM meal_table WHERE kind = 'Lunch' AND date LIKE :date ORDER BY id DESC")
//    LiveData<List<Meal>> getLunch(String date);
//
//    @Query("SELECT * FROM meal_table WHERE kind = 'Dinner' AND date LIKE :date ORDER BY id DESC")
//    LiveData<List<Meal>> getDinner(String date);
//
//    @Query("SELECT * FROM meal_table WHERE kind = 'Snacks' AND date LIKE :date ORDER BY id DESC")
//    LiveData<List<Meal>> getSnacks(String date);

    @Query("SELECT * FROM meal_table WHERE kind = 'Breakfast' ORDER BY id DESC")
    LiveData<List<Meal>> getBreakfast();

    @Query("SELECT * FROM meal_table WHERE kind = 'Lunch' ORDER BY id DESC")
    LiveData<List<Meal>> getLunch();

    @Query("SELECT * FROM meal_table WHERE kind = 'Dinner' ORDER BY id DESC")
    LiveData<List<Meal>> getDinner();

    @Query("SELECT * FROM meal_table WHERE kind = 'Snacks' ORDER BY id DESC")
    LiveData<List<Meal>> getSnacks();
}

