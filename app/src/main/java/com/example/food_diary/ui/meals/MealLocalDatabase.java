package com.example.food_diary.ui.meals;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Meal.class}, version = 1)
public abstract class MealLocalDatabase extends RoomDatabase{

    private static MealLocalDatabase instance;

    public abstract MealDao MealDao();

    public static synchronized MealLocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    MealLocalDatabase.class, "Meal_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new MealLocalDatabase.PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private MealDao MealDao;

        private PopulateDbAsyncTask(MealLocalDatabase db) {
            MealDao = db.MealDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            MealDao.insert(new Meal("email", "12.12.1212", "bigos", "Lunch", 120, 250, 30, 20, 20, 3));
            MealDao.insert(new Meal("email", "12.12.1212", "galareta", "Lunch", 20, 600, 50, 40, 10, 1));
            MealDao.insert(new Meal("email", "12.12.1212", "golabki", "Lunch", 180, 900, 60, 20, 60, 5));
            return null;
        }
    }
}
