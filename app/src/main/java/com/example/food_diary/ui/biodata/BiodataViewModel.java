package com.example.food_diary.ui.biodata;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class BiodataViewModel extends AndroidViewModel {

    private BiodataRepository repository;
    private LiveData<List<Biodata>> allBiodata;

    public BiodataViewModel(@NonNull Application application) {
        super(application);
        repository = new BiodataRepository(application);
        allBiodata = repository.getAll();
    }

    public void insert(Biodata biodata) {
        repository.insert(biodata);
    }
    public void update(Biodata biodata) {
        repository.update(biodata);
    }
    public void delete(Biodata biodata) {
        repository.delete(biodata);
    }
    public void deleteAll(Biodata biodata) {
        repository.deleteAll();
    }

    public LiveData<List<Biodata>> getAll() {
        return allBiodata;
    }
}
