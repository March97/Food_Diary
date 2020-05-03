package com.example.food_diary.ui.meals;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_diary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import static android.app.Activity.RESULT_OK;

public class MealFragment extends Fragment {

    public static final int ADD_MEAL_REQUEST = 1;
    public static final int EDIT_MEAL_REQUEST = 2;

    private String email = "admin";
    private int height = 150;

    private MealViewModel mealViewModel;
    private ArrayList<Meal> mealList;
    private DatePickerDialog.OnDateSetListener dateSetListener;
    private TextView date_tv;
    String date;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_meal_list, container, false);

        mealViewModel = ViewModelProviders.of(this).get(MealViewModel.class);

        date_tv = root.findViewById(R.id.meal_date_tv);

        Date c = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        date = sdf.format(c);
        date_tv.setText("   " + date);
//        mealViewModel.setDate(date);
//        SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = sharedPref.edit();
//        editor.putString(getString(R.string.saved_date), date);
//        editor.commit();

        date_tv.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(view.getContext(),
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        dateSetListener,
                        year, month, day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        dateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                //month += 1;
                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, year);
                cal.set(Calendar.MONTH, month);
                cal.set(Calendar.DATE, dayOfMonth);
                Date dateCal = cal.getTime();
                //Log.d(TAG, "onDateSet: " + year + "-" + month + "-" + dayOfMonth);
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                date = sdf.format(dateCal);
                date_tv.setText("   " + date);
//                mealViewModel.setDate(date);
//                SharedPreferences sharedPref = getActivity().getPreferences(Context.MODE_PRIVATE);
//                SharedPreferences.Editor editor = sharedPref.edit();
//                editor.putString(getString(R.string.saved_date), date);
//                editor.commit();
            }
        };

        FloatingActionButton buttonAddOrder = root.findViewById(R.id.meal_fab);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), MealAddActivity.class);
                intent.putExtra(MealAddActivity.EXTRA_DATE, date);
                startActivityForResult(intent, ADD_MEAL_REQUEST);
            }
        });



        RecyclerView breakfastRecyclerView = root.findViewById(R.id.meal_breakfast_rv);
        breakfastRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        breakfastRecyclerView.setHasFixedSize(true);

        RecyclerView lunchRecyclerView = root.findViewById(R.id.meal_lunch_rv);
        lunchRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        lunchRecyclerView.setHasFixedSize(true);

        RecyclerView dinnerRecyclerView = root.findViewById(R.id.meal_dinner_rv);
        dinnerRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        dinnerRecyclerView.setHasFixedSize(true);


        RecyclerView snacksRecyclerView = root.findViewById(R.id.meal_snacks_rv);
        snacksRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        snacksRecyclerView.setHasFixedSize(true);

        final MealAdapter breakfastAdapter= new MealAdapter();
        final MealAdapter lunchAdapter= new MealAdapter();
        final MealAdapter dinnerAdapter= new MealAdapter();
        final MealAdapter snacksAdapter= new MealAdapter();
        breakfastRecyclerView.setAdapter(breakfastAdapter);
        dinnerRecyclerView.setAdapter(dinnerAdapter);
        lunchRecyclerView.setAdapter(lunchAdapter);
        snacksRecyclerView.setAdapter(snacksAdapter);



        mealViewModel.getBreakfast().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meal) {
                breakfastAdapter.setMeal(meal);
                mealList = (ArrayList) meal;
                //Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        mealViewModel.getLunch().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meal) {
                lunchAdapter.setMeal(meal);
                mealList = (ArrayList) meal;
                //Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        mealViewModel.getDinner().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meal) {
                dinnerAdapter.setMeal(meal);
                mealList = (ArrayList) meal;
                //Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        mealViewModel.getSnacks().observe(this, new Observer<List<Meal>>() {
            @Override
            public void onChanged(List<Meal> meal) {
                snacksAdapter.setMeal(meal);
                mealList = (ArrayList) meal;
                //Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mealViewModel.delete(breakfastAdapter.getMealAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "meal deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(breakfastRecyclerView);

        breakfastAdapter.setOnItemClickListener(new MealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal) {
                Toast.makeText(getContext(), "Update meal", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MealAddActivity.class);
                intent.putExtra(MealAddActivity.EXTRA_ID, meal.getId());
                intent.putExtra(MealAddActivity.EXTRA_EMAIL, meal.getEmail());
                intent.putExtra(MealAddActivity.EXTRA_DATE, meal.getDate());
                intent.putExtra(MealAddActivity.EXTRA_NAME, meal.getName());
                intent.putExtra(MealAddActivity.EXTRA_KIND, meal.getKind());
                intent.putExtra(MealAddActivity.EXTRA_MASS, meal.getMass());
                intent.putExtra(MealAddActivity.EXTRA_ENERGY, meal.getEnergy());
                intent.putExtra(MealAddActivity.EXTRA_CARBS, meal.getCarbs());
                intent.putExtra(MealAddActivity.EXTRA_PROTEIN, meal.getProtein());
                intent.putExtra(MealAddActivity.EXTRA_FAT, meal.getFat());
                intent.putExtra(MealAddActivity.EXTRA_PORTIONS, meal.getPortions());
                startActivityForResult(intent, EDIT_MEAL_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mealViewModel.delete(lunchAdapter.getMealAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "meal deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(lunchRecyclerView);

        lunchAdapter.setOnItemClickListener(new MealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal) {
                Toast.makeText(getContext(), "Update meal", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MealAddActivity.class);
                intent.putExtra(MealAddActivity.EXTRA_ID, meal.getId());
                intent.putExtra(MealAddActivity.EXTRA_EMAIL, meal.getEmail());
                intent.putExtra(MealAddActivity.EXTRA_DATE, meal.getDate());
                intent.putExtra(MealAddActivity.EXTRA_NAME, meal.getName());
                intent.putExtra(MealAddActivity.EXTRA_KIND, meal.getKind());
                intent.putExtra(MealAddActivity.EXTRA_MASS, meal.getMass());
                intent.putExtra(MealAddActivity.EXTRA_ENERGY, meal.getEnergy());
                intent.putExtra(MealAddActivity.EXTRA_CARBS, meal.getCarbs());
                intent.putExtra(MealAddActivity.EXTRA_PROTEIN, meal.getProtein());
                intent.putExtra(MealAddActivity.EXTRA_FAT, meal.getFat());
                intent.putExtra(MealAddActivity.EXTRA_PORTIONS, meal.getPortions());
                startActivityForResult(intent, EDIT_MEAL_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mealViewModel.delete(dinnerAdapter.getMealAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "meal deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(dinnerRecyclerView);

        snacksAdapter.setOnItemClickListener(new MealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal) {
                Toast.makeText(getContext(), "Update meal", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MealAddActivity.class);
                intent.putExtra(MealAddActivity.EXTRA_ID, meal.getId());
                intent.putExtra(MealAddActivity.EXTRA_EMAIL, meal.getEmail());
                intent.putExtra(MealAddActivity.EXTRA_DATE, meal.getDate());
                intent.putExtra(MealAddActivity.EXTRA_NAME, meal.getName());
                intent.putExtra(MealAddActivity.EXTRA_KIND, meal.getKind());
                intent.putExtra(MealAddActivity.EXTRA_MASS, meal.getMass());
                intent.putExtra(MealAddActivity.EXTRA_ENERGY, meal.getEnergy());
                intent.putExtra(MealAddActivity.EXTRA_CARBS, meal.getCarbs());
                intent.putExtra(MealAddActivity.EXTRA_PROTEIN, meal.getProtein());
                intent.putExtra(MealAddActivity.EXTRA_FAT, meal.getFat());
                intent.putExtra(MealAddActivity.EXTRA_PORTIONS, meal.getPortions());
                startActivityForResult(intent, EDIT_MEAL_REQUEST);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                mealViewModel.delete(snacksAdapter.getMealAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "meal deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(snacksRecyclerView);

        dinnerAdapter.setOnItemClickListener(new MealAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Meal meal) {
                Toast.makeText(getContext(), "Update meal", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), MealAddActivity.class);
                intent.putExtra(MealAddActivity.EXTRA_ID, meal.getId());
                intent.putExtra(MealAddActivity.EXTRA_EMAIL, meal.getEmail());
                intent.putExtra(MealAddActivity.EXTRA_DATE, meal.getDate());
                intent.putExtra(MealAddActivity.EXTRA_NAME, meal.getName());
                intent.putExtra(MealAddActivity.EXTRA_KIND, meal.getKind());
                intent.putExtra(MealAddActivity.EXTRA_MASS, meal.getMass());
                intent.putExtra(MealAddActivity.EXTRA_ENERGY, meal.getEnergy());
                intent.putExtra(MealAddActivity.EXTRA_CARBS, meal.getCarbs());
                intent.putExtra(MealAddActivity.EXTRA_PROTEIN, meal.getProtein());
                intent.putExtra(MealAddActivity.EXTRA_FAT, meal.getFat());
                intent.putExtra(MealAddActivity.EXTRA_PORTIONS, meal.getPortions());
                startActivityForResult(intent, EDIT_MEAL_REQUEST);
            }
        });

        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_MEAL_REQUEST && resultCode == RESULT_OK) {
            String email = data.getStringExtra(MealAddActivity.EXTRA_EMAIL);
            String date = data.getStringExtra(MealAddActivity.EXTRA_DATE);
            String name = data.getStringExtra(MealAddActivity.EXTRA_NAME);
            String kind = data.getStringExtra(MealAddActivity.EXTRA_KIND);
            double mass = data.getDoubleExtra(MealAddActivity.EXTRA_MASS, 1.0);
            int energy = data.getIntExtra(MealAddActivity.EXTRA_ENERGY, 1);
            int carbs = data.getIntExtra(MealAddActivity.EXTRA_CARBS, 1);
            int protein = data.getIntExtra(MealAddActivity.EXTRA_PROTEIN, 1);
            int fat = data.getIntExtra(MealAddActivity.EXTRA_FAT, 1);
            int portions = data.getIntExtra(MealAddActivity.EXTRA_PORTIONS, 1);


            Meal meal = new Meal(email, date, name, kind, mass, energy, carbs, protein, fat, portions);
            mealViewModel.insert(meal);
            Toast.makeText(getContext(), "Meal saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_MEAL_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(MealAddActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Meal can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String email = data.getStringExtra(MealAddActivity.EXTRA_EMAIL);
            String date = data.getStringExtra(MealAddActivity.EXTRA_DATE);
            String name = data.getStringExtra(MealAddActivity.EXTRA_NAME);
            String kind = data.getStringExtra(MealAddActivity.EXTRA_KIND);
            double mass = data.getDoubleExtra(MealAddActivity.EXTRA_MASS, 1.0);
            int energy = data.getIntExtra(MealAddActivity.EXTRA_ENERGY, 1);
            int carbs = data.getIntExtra(MealAddActivity.EXTRA_CARBS, 1);
            int protein = data.getIntExtra(MealAddActivity.EXTRA_PROTEIN, 1);
            int fat = data.getIntExtra(MealAddActivity.EXTRA_FAT, 1);
            int portions = data.getIntExtra(MealAddActivity.EXTRA_PORTIONS, 1);

            Meal meal = new Meal(email, date, name, kind, mass, energy, carbs, protein, fat, portions);
            meal.setId(id);
            mealViewModel.update(meal);

            Toast.makeText(getContext(), "Meal updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Meal can't be saved", Toast.LENGTH_SHORT).show();
        }
    }
}