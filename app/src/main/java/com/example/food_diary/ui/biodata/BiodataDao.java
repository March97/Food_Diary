package com.example.food_diary.ui.biodata;

public interface BiodataDao {

    void insert(Biodata biodata);

    void update(Biodata biodata);

    void delete(Biodata biodata);

    void deleteAll();

    void get(Biodata biodata);

    void getALL();
}
