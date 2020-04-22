package com.example.food_diary.ui.biodata;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_diary.R;

import java.util.ArrayList;
import java.util.List;

public class BiodataAdapter extends RecyclerView.Adapter<BiodataAdapter.BiodataHolder> {

    private List<Biodata> biodata = new ArrayList<>();
    @NonNull
    @Override
    public BiodataHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_biodata, parent, false);
        return new BiodataHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull BiodataHolder holder, int position) {
        Biodata currentBiodata = biodata.get(position);
        holder.id_tv.setText(String.valueOf(currentBiodata.getId()));
        holder.date_tv.setText(currentBiodata.getDate());
        holder.mass_tv.setText(String.valueOf(currentBiodata.getMass()) + "kg");
        holder.bmi_tv.setText(String.valueOf(currentBiodata.getBmi()));
    }

    @Override
    public int getItemCount() {
        return biodata.size();
    }

    public void setBiodata(List<Biodata> biodata) {
        this.biodata = biodata;
        notifyDataSetChanged();
    }

    class BiodataHolder extends RecyclerView.ViewHolder {
        private TextView id_tv;
        private TextView date_tv;
        private TextView mass_tv;
        private TextView bmi_tv;

        public BiodataHolder(View itemView) {
            super(itemView);
            id_tv = itemView.findViewById(R.id.biodata_id_tv);
            date_tv = itemView.findViewById(R.id.biodata_date_tv);
            mass_tv = itemView.findViewById(R.id.biodata_mass_tv);
            bmi_tv = itemView.findViewById(R.id.biodata_bmi_tv);
        }
    }
}
