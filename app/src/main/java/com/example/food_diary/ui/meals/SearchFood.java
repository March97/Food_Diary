package com.example.food_diary.ui.meals;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;

import com.example.food_diary.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class SearchFood extends AppCompatActivity implements LifecycleOwner {
    public static final String EXTRA_NAME = "com.example.food_diary.ui.mealsS.EXTRA_NAME";

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
    Map<String, Object> combo;
    LinkedHashMap<String, Object> comboL;
    List<Map<String, Object>> listaMap;

    public SearchFood() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_search_food);
        listViewOfItems = findViewById(R.id.listOfFood);
        inputSearch = findViewById(R.id.searched_item);

        listaMap = new ArrayList<>();
        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                clearAll();

                searchFoodViewModel.changeSearchedFoodName(s.toString()).observe(SearchFood.this, new Observer<Map<String, Object>>() {
                    @Override
                    public void onChanged(Map<String, Object> stringObjectMap) {

                        combo = stringObjectMap;


                        listItems.add(stringObjectMap.get("product_name").toString());
                        listaMap.add(combo);
                        List<String> arr = new ArrayList<>(listItems);
                        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(
                                SearchFood.this,
                                android.R.layout.simple_list_item_1,
                                arr);


                        comboL = new LinkedHashMap<>(combo);
                        listViewOfItems.setAdapter(arrayAdapter);


                    }
                });
                listViewOfItems.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        whenSelectedMeal(position);
                    }
                });

            }
        });


    }

    private void clearAll() {
        if (combo != null && combo.size() != 0) {
            combo.clear();
        }
        if (listaMap != null && listaMap.size() != 0) listaMap.clear();
        if (listItems != null && listItems.size() != 0)
            listItems.clear();


    }


    private void whenSelectedMeal(int pos) {

        Intent data = new Intent();


        data.putExtra(EXTRA_NAME, listaMap.get(pos).get("product_name").toString());
        data.putExtra(EXTRA_MASS, "100");
        data.putExtra(EXTRA_PORTIONS, "1");
        if (listaMap.get(pos).get("energy_100g")!=null)
            data.putExtra(EXTRA_ENERGY, listaMap.get(pos).get("energy_100g").toString());
        if (listaMap.get(pos).get("carbohydrates_100g")!=null)
            data.putExtra(EXTRA_CARBS, listaMap.get(pos).get("carbohydrates_100g").toString());
        if (listaMap.get(pos).get("proteins_100g")!=null)
            data.putExtra(EXTRA_PROTEIN, listaMap.get(pos).get("proteins_100g").toString());
        if (listaMap.get(pos).get("fat_100g")!=null)
            data.putExtra(EXTRA_FAT, listaMap.get(pos).get("fat_100g").toString());


        setResult(RESULT_OK, data);
        finish();
    }


}
