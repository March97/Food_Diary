package com.example.food_diary.ui.meals;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import java.util.List;

public class MealRepository {

    private MealDao mealDao;
    private LiveData<List<Meal>> allMeal;
    private LiveData<List<Meal>> breakfast;
    private LiveData<List<Meal>> lunch;
    private LiveData<List<Meal>> dinner;
    private LiveData<List<Meal>> snacks;
    //private String date;

    public MealRepository(Application application) {
        MealLocalDatabase database = MealLocalDatabase.getInstance(application);
        mealDao = database.MealDao();
        allMeal = mealDao.getAll();
        breakfast = mealDao.getBreakfast();
        lunch = mealDao.getLunch();
        dinner = mealDao.getDinner();
        snacks = mealDao.getSnacks();;
    }

    public void insert(Meal meal) {
        new MealRepository.InsertMealAsyncTask(mealDao).execute(meal);
    }

    public void update(Meal meal) {
        new MealRepository.UpdateMealAsyncTask(mealDao).execute(meal);
    }

    public void delete(Meal meal) {
        new MealRepository.DeleteMealAsyncTask(mealDao).execute(meal);
    }

    public void deleteAll() {
        new MealRepository.DeleteallMealAsyncTask(mealDao).execute();
    }

//    public void setDate(String date) {
//        breakfast = mealDao.getBreakfast(date);
//        lunch = mealDao.getLunch(date);
//        dinner = mealDao.getDinner(date);
//        snacks = mealDao.getSnacks(date);
//    }

    public LiveData<List<Meal>> getAll() {
        return  allMeal;
    }

    public LiveData<List<Meal>> getBreakfast() {
        breakfast = mealDao.getBreakfast();
        return  breakfast;
    }
    public LiveData<List<Meal>> getLunch() {
        lunch = mealDao.getLunch();
        return  lunch;
    }
    public LiveData<List<Meal>> getDinner() {
        dinner = mealDao.getDinner();
        return  dinner;
    }
    public LiveData<List<Meal>> getSnacks() {
        snacks = mealDao.getSnacks();
        return  snacks;
    }

    private static class InsertMealAsyncTask extends AsyncTask<Meal, Void, Void> {
        private MealDao mealDao;
        private InsertMealAsyncTask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meal) {
            mealDao.insert(meal[0]);
            return null;
        }
    }

    private static class UpdateMealAsyncTask extends AsyncTask<Meal, Void, Void> {
        private MealDao mealDao;
        private UpdateMealAsyncTask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meal) {
            mealDao.update(meal[0]);
            return null;
        }
    }

    private static class DeleteMealAsyncTask extends AsyncTask<Meal, Void, Void> {
        private MealDao mealDao;
        private DeleteMealAsyncTask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Meal... meal) {
            mealDao.delete(meal[0]);
            return null;
        }
    }

    private static class DeleteallMealAsyncTask extends AsyncTask<Void, Void, Void> {
        private MealDao mealDao;
        private DeleteallMealAsyncTask(MealDao mealDao) {
            this.mealDao = mealDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            mealDao.deleteAll();
            return null;
        }
    }
}
