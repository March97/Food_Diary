package com.example.food_diary.ui.biodata;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_diary.R;

import java.util.List;

public class BiodataFragment extends Fragment {

    private BiodataViewModel biodataViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_biodata_list, container, false);

        biodataViewModel = ViewModelProviders.of(this).get(BiodataViewModel.class);

        RecyclerView recyclerView = root.findViewById(R.id.biodata_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final BiodataAdapter adapter = new BiodataAdapter();
        recyclerView.setAdapter(adapter);

        biodataViewModel.getAll().observe(this, new Observer<List<Biodata>>() {
            @Override
            public void onChanged(List<Biodata> biodata) {
                adapter.setBiodata(biodata);
                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }
}
