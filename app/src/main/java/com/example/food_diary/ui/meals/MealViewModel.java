package com.example.food_diary.ui.meals;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Transformations;

import java.util.List;

public class MealViewModel extends AndroidViewModel {

    private MealRepository repository;
    private LiveData<List<Meal>> allMeals;
    private LiveData<List<Meal>> breakfast;
    private LiveData<List<Meal>> lunch;
    private LiveData<List<Meal>> dinner;
    private LiveData<List<Meal>> snacks;
    private MutableLiveData<String> date = new MutableLiveData<String>();

    public MealViewModel(@NonNull Application application) {
        super(application);
        repository = new MealRepository(application);
        allMeals = repository.getAll();
        breakfast = repository.getBreakfast();
        lunch = repository.getLunch();
        dinner = repository.getDinner();
        snacks = repository.getSnacks();
//        breakfast = Transformations.switchMap(date, v -> repository.getBreakfast(v));
//        lunch = Transformations.switchMap(date, v -> repository.getLunch(v));
//        dinner = Transformations.switchMap(date, v -> repository.getDinner(v));
//        snacks = Transformations.switchMap(date, v -> repository.getSnacks(v));
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

//    private void updateMeals(String date) {
//        breakfast = repository.getBreakfast(date);
//        lunch = repository.getLunch(date);
//        dinner = repository.getDinner(date);
//        snacks = repository.getSnacks(date);
//    }

    public MutableLiveData<String> getDate() {
        return date;
    }

//    public void setDate(String filter) {
//        date.setValue(filter);
//        repository.setDate(filter);
//        updateMeals(date.getValue());
//    }

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
