package com.example.food_diary.ui.meals;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository repository;
    private LiveData<List<Meal>> allMeals;
    private LiveData<List<Meal>> breakfast;
    private LiveData<List<Meal>> lunch;
    private LiveData<List<Meal>> dinner;
    private LiveData<List<Meal>> snacks;

    public MealViewModel(@NonNull Application application) {
        super(application);
        repository = new MealRepository(application);
        allMeals = repository.getAll();
        breakfast = repository.getBreakfast();
        lunch = repository.getLunch();
        dinner = repository.getDinner();
        snacks = repository.getSnacks();
    }

    public void insert(Meal meal) {
        repository.insert(meal);
    }
    public void update(Meal meal) {
        repository.update(meal);
    }
    public void delete(Meal meal) {
        repository.delete(meal);
    }
    public void deleteAll(Meal meal) {
        repository.deleteAll();
    }

    public LiveData<List<Meal>> getAll() {
        return allMeals;
    }

    public LiveData<List<Meal>> getBreakfast() {
        return breakfast;
    }

    public LiveData<List<Meal>> getLunch() {
        return lunch;
    }

    public LiveData<List<Meal>> getDinner() {
        return dinner;
    }

    public LiveData<List<Meal>> getSnacks() {
        return snacks;
    }
}
