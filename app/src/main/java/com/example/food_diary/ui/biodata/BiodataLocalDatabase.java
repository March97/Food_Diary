package com.example.food_diary.ui.biodata;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Biodata.class}, version = 1)
public abstract class BiodataLocalDatabase extends RoomDatabase {

    private static BiodataLocalDatabase instance;

    public abstract BiodataDao biodataDao();

    public static synchronized BiodataLocalDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    BiodataLocalDatabase.class, "biodata_database")
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
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private BiodataDao biodataDao;

        private PopulateDbAsyncTask(BiodataLocalDatabase db) {
            biodataDao = db.biodataDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            biodataDao.insert(new Biodata(1, "12.12.1212", 3, 4, "email"));
            biodataDao.insert(new Biodata(1, "12.12.1212", 3, 4, "email"));
            biodataDao.insert(new Biodata(1, "12.12.1212", 3, 4, "email"));
            return null;
        }
    }
}
