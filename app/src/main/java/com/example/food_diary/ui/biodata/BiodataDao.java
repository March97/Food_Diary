package com.example.food_diary.ui.biodata;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface BiodataDao {

    @Insert
    void insert(Biodata biodata);

    @Update
    void update(Biodata biodata);

    @Delete
    void delete(Biodata biodata);

    @Query("DELETE FROM biodata_table")
    void deleteAll();

    //void get(Biodata biodata);

    @Query("SELECT * FROM biodata_table ORDER BY id DESC")
    LiveData<List<Biodata>> getAll();
}
