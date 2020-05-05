package com.example.food_diary.ui.meals;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.food_diary.R;

import java.nio.BufferUnderflowException;

public class MealAddActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.food_diary.ui.meals.EXTRA_ID";

    public static final String EXTRA_EMAIL =
            "com.example.food_diary.ui.meals.EXTRA_EMAIL";

    public static final String EXTRA_DATE =
            "com.example.food_diary.ui.meals.EXTRA_DATE";

    public static final String EXTRA_NAME =
            "com.example.food_diary.ui.meals.EXTRA_NAME";

    public static final String EXTRA_KIND =
            "com.example.food_diary.ui.meals.EXTRA_KIND";

    public static final String EXTRA_MASS =
            "com.example.food_diary.ui.meals.EXTRA_MASS";

    public static final String EXTRA_ENERGY =
            "com.example.food_diary.ui.meals.EXTRA_ENERGY";

    public static final String EXTRA_CARBS =
            "com.example.food_diary.ui.meals.EXTRA_CARBS";

    public static final String EXTRA_PROTEIN =
            "com.example.food_diary.ui.meals.EXTRA_PROTEIN";

    public static final String EXTRA_FAT =
            "com.example.food_diary.ui.meals.EXTRA_FAT";

    public static final String EXTRA_PORTIONS =
            "com.example.food_diary.ui.meals.EXTRA_PORTIONS";

    private EditText name_et;
    private Spinner spinner;
    private EditText mass_et;
    private EditText portions_et;
    private EditText energy_et;
    private EditText carb_et;
    private EditText protein_et;
    private EditText fat_et;
    private Button ok_btn;
    private String date;
    MealRemoteDatabase mealRemoteDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mealRemoteDatabase = new MealRemoteDatabase();

        setContentView(R.layout.activity_meal_add);

        name_et = findViewById(R.id.meal_add_name);
        mass_et = findViewById(R.id.meal_add_mass);
        portions_et = findViewById(R.id.meal_add_portions);
        energy_et = findViewById(R.id.meal_add_energy);
        carb_et = findViewById(R.id.meal_add_carb);
        protein_et = findViewById(R.id.meal_add_protein);
        fat_et = findViewById(R.id.meal_add_fat);
        ok_btn = findViewById(R.id.meal_add_button);

        spinner = findViewById(R.id.meal_add_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.kind_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);


        Intent intent = getIntent();
        date = intent.getStringExtra(EXTRA_DATE);
        System.out.println(date);

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit meal");
            name_et.setText(intent.getStringExtra(EXTRA_NAME));
            mass_et.setText(String.valueOf(intent.getIntExtra(EXTRA_MASS, 1)));
            portions_et.setText(String.valueOf(intent.getIntExtra(EXTRA_PORTIONS, 1)));
            energy_et.setText(String.valueOf(intent.getIntExtra(EXTRA_ENERGY, 1)));
            carb_et.setText(String.valueOf(intent.getIntExtra(EXTRA_CARBS, 1)));
            protein_et.setText(String.valueOf(intent.getIntExtra(EXTRA_PROTEIN, 1)));
            fat_et.setText(String.valueOf(intent.getIntExtra(EXTRA_FAT, 1)));

        } else {
            setTitle("Add meal");
        }


        ok_btn.setOnClickListener(v -> saveMeal());

    }


    private void saveMeal() {
        String name = name_et.getText().toString();
        String kind = String.valueOf(spinner.getSelectedItem());
        int mass = Integer.valueOf(mass_et.getText().toString());
        int portions = Integer.valueOf(portions_et.getText().toString());
        int energy = Integer.valueOf(energy_et.getText().toString());
        int carb = Integer.valueOf(carb_et.getText().toString());
        int fat = Integer.valueOf(protein_et.getText().toString());
        int protein = Integer.valueOf(fat_et.getText().toString());


        if(energy <= 0) {
            Toast.makeText(this, "Please insert proper values", Toast.LENGTH_SHORT).show();
            return;
        }

        mealRemoteDatabase.createInstance();

        Intent data = new Intent();

        data.putExtra(EXTRA_NAME, name);
        data.putExtra(EXTRA_MASS, mass);
        data.putExtra(EXTRA_PORTIONS, portions);
        data.putExtra(EXTRA_ENERGY, energy);
        data.putExtra(EXTRA_CARBS, carb);
        data.putExtra(EXTRA_PROTEIN, protein);
        data.putExtra(EXTRA_FAT, fat);
        data.putExtra(EXTRA_KIND, kind);
        data.putExtra(EXTRA_DATE, date);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
