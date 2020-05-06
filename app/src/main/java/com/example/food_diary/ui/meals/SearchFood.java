package com.example.food_diary.ui.meals;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;

import com.example.food_diary.R;

import java.util.ArrayList;
import java.util.HashMap;

public class SearchFood extends AppCompatActivity {

    public static final String EXTRA_MASS = "com.example.food_diary.ui.mealsS.EXTRA_MASS";
    public static final String EXTRA_ENERGY = "com.example.food_diary.ui.mealsS.EXTRA_ENERGY";
    ;
    public static final String EXTRA_CARBS = "com.example.food_diary.ui.mealsS.EXTRA_CARBS";
    ;
    public static final String EXTRA_PROTEIN = "com.example.food_diary.ui.mealsS.EXTRA_PROTEIN";
    ;
    public static final String EXTRA_FAT = "com.example.food_diary.ui.mealsS.EXTRA_FAT";
    ;
    public static final String EXTRA_PORTIONS = "com.example.food_diary.ui.mealsS.EXTRA_PORTIONS";

    SearchFoodViewModel searchFoodViewModel = new SearchFoodViewModel();
    private ArrayList<String> listItems = new ArrayList<>();
    private ArrayList<HashMap<String, String>> listItemsMap = new ArrayList<>();
    private ListView listViewOfItems;
    private EditText inputSearch;
    private Button doSpecyfikacji;
    private String date;
    private Pair<ArrayList<String>, ArrayList<HashMap<String, String>>> c1;

    public SearchFood() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_food);

        listViewOfItems = findViewById(R.id.listOfFood);
        inputSearch = findViewById(R.id.searched_item);


        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                searchFoodViewModel.changeSearchedFoodName("");
              /*  searchFoodViewModel.changeSearchedFoodName(s.toString()).observe(getViewLifecycleOwner(), new Observer<Pair<ArrayList<String>, ArrayList<HashMap<String, String>>>>() {
                    @Override
                    public void onChanged(Pair<ArrayList<String>, ArrayList<HashMap<String, String>>> arrayListArrayListPair) {

                    }

                }*/

            }



        @Override
        public void afterTextChanged (Editable s){


        }
    });


}


    private void whenSelectedMeal() {

        Intent data = new Intent();
/*

        data.putExtra(EXTRA_MASS, mass);
        data.putExtra(EXTRA_PORTIONS, portions);
        data.putExtra(EXTRA_ENERGY, energy);
        data.putExtra(EXTRA_CARBS, carb);
        data.putExtra(EXTRA_PROTEIN, protein);
        data.putExtra(EXTRA_FAT, fat);
*/


        setResult(RESULT_OK, data);
        finish();
    }


}
