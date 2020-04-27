package com.example.food_diary.ui.meals;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_diary.R;
import com.example.food_diary.ui.biodata.Biodata;
import com.example.food_diary.ui.biodata.BiodataAdapter;

import java.util.ArrayList;
import java.util.List;

public class MealAdapter extends RecyclerView.Adapter<MealAdapter.MealHolder> {

    private List<Meal> meals = new ArrayList<>();
    private MealAdapter.OnItemClickListener listener;

    @NonNull
    @Override
    public MealAdapter.MealHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_meal, parent, false);
        return new MealAdapter.MealHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MealAdapter.MealHolder holder, int position) {
        Meal currentMeal = meals.get(position);
        holder.id_tv.setText(String.valueOf(currentMeal.getId()));
        holder.name_tv.setText(currentMeal.getName());
        holder.energy_tv.setText(String.valueOf(currentMeal.getEnergy()));
        holder.protein_tv.setText(String.valueOf(currentMeal.getProtein()));
        holder.fat_tv.setText(String.valueOf(currentMeal.getFat()));
        holder.carb_tv.setText(String.valueOf(currentMeal.getCarbs()));
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public Meal getMealAt(int position) {
        return meals.get(position);
    }

    public void setMeal(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    class MealHolder extends RecyclerView.ViewHolder {
        private TextView id_tv;
        private TextView name_tv;
        private TextView energy_tv;
        private TextView protein_tv;
        private TextView fat_tv;
        private TextView carb_tv;

        public MealHolder(View itemView) {
            super(itemView);
            id_tv = itemView.findViewById(R.id.meal_id_tv);
            name_tv = itemView.findViewById(R.id.meal_name_tv);
            energy_tv = itemView.findViewById(R.id.meal_energy_tv);
            protein_tv = itemView.findViewById(R.id.meal_protein_tv);
            fat_tv = itemView.findViewById(R.id.meal_fat_tv);
            carb_tv = itemView.findViewById(R.id.meal_carb_tv);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION)
                        listener.onItemClick(meals.get(position));
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Meal meal);
    }

    public void setOnItemClickListener(MealAdapter.OnItemClickListener listener) {
        this.listener = listener;
    }
}
