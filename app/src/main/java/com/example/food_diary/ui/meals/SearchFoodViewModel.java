package com.example.food_diary.ui.meals;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class SearchFoodViewModel extends ViewModel {
    //private MutableLiveData<ArrayList<HashMap<String, String>>> allBiodatas;
    private MutableLiveData<ArrayList<String>> selectedFoods;
    private ArrayList<String> listItems = new ArrayList<>();
    private MutableLiveData<ArrayList<HashMap<String, String>>> listItemsMap;
    private MutableLiveData<Map<String,Object>> combo = new MutableLiveData<>();

    private SearchFoodRepository repository ;


//    public BiodataViewModel() {
//    }

    public void insert(HashMap<String, String> biodata) {
        //repository.insert(biodata);
    }

    public void update(String input) {
        //repository.update(biodata);
    }

    public void delete(HashMap<String, String> biodata) {
        //repository.delete(biodata);
    }

    public MutableLiveData<Map<String, Object>> changeSearchedFoodName(String input) {
        combo = new MutableLiveData<>();
        repository = new SearchFoodRepository(input);
        combo = repository.getItem();


        return combo;
    }
}
