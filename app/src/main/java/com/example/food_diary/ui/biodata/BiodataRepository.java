package com.example.food_diary.ui.biodata;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class BiodataRepository {

    private BiodataDao biodataDao;
    private LiveData<List<Biodata>> allBiodata;

    public BiodataRepository(Application application) {
        BiodataLocalDatabase database = BiodataLocalDatabase.getInstance(application);
        biodataDao = database.biodataDao();
        allBiodata = biodataDao.getAll();
    }

    public void insert(Biodata Biodata) {
        new InsertBiodataAsyncTask(biodataDao).execute(Biodata);
    }

    public void update(Biodata Biodata) {
        new UpdateBiodataAsyncTask(biodataDao).execute(Biodata);

    }

    public void delete(Biodata Biodata) {
        new DeleteBiodataAsyncTask(biodataDao).execute(Biodata);
    }

    public void deleteAll() {
        new DeleteallBiodataAsyncTask(biodataDao).execute();
    }

    public LiveData<List<Biodata>> getAll() {
        return  allBiodata;
    }

    private static class InsertBiodataAsyncTask extends AsyncTask<Biodata, Void, Void> {
        private BiodataDao biodataDao;
        private InsertBiodataAsyncTask(BiodataDao biodataDao) {
            this.biodataDao = biodataDao;
        }

        @Override
        protected Void doInBackground(Biodata... Biodatas) {
            biodataDao.insert(Biodatas[0]);
            return null;
        }
    }

    private static class UpdateBiodataAsyncTask extends AsyncTask<Biodata, Void, Void> {
        private BiodataDao biodataDao;
        private UpdateBiodataAsyncTask(BiodataDao biodataDao) {
            this.biodataDao = biodataDao;
        }

        @Override
        protected Void doInBackground(Biodata... Biodatas) {
            biodataDao.update(Biodatas[0]);
            return null;
        }
    }

    private static class DeleteBiodataAsyncTask extends AsyncTask<Biodata, Void, Void> {
        private BiodataDao biodataDao;
        private DeleteBiodataAsyncTask(BiodataDao biodataDao) {
            this.biodataDao = biodataDao;
        }

        @Override
        protected Void doInBackground(Biodata... Biodatas) {
            biodataDao.delete(Biodatas[0]);
            return null;
        }
    }

    private static class DeleteallBiodataAsyncTask extends AsyncTask<Void, Void, Void> {
        private BiodataDao biodataDao;
        private DeleteallBiodataAsyncTask(BiodataDao biodataDao) {
            this.biodataDao = biodataDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            biodataDao.deleteAll();
            return null;
        }
    }
}
