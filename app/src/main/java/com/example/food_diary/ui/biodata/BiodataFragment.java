package com.example.food_diary.ui.biodata;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.food_diary.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class BiodataFragment extends Fragment {

    public static final int ADD_BIODATA_REQUEST = 1;
    public static final int EDIT_BIODATA_REQUEST = 2;

    private String email = "admin";
    //private int height = 180;
    private FirebaseAuth mAuth;

    private BiodataViewModel biodataViewModel;
    private ArrayList<Biodata> biodataList;
    //SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_biodata_list, container, false);

        biodataList = new ArrayList<Biodata>();
        mAuth = FirebaseAuth.getInstance();
        FirebaseUser currentUser = mAuth.getCurrentUser();

        try {
            email = currentUser.getEmail();
        }
        catch (NullPointerException e) {
            System.out.println("User email null");
        }

        FloatingActionButton buttonAddOrder = root.findViewById(R.id.biodata_fab);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), BiodataAddActivity.class);
                startActivityForResult(intent, ADD_BIODATA_REQUEST);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.biodata_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final BiodataAdapter adapter = new BiodataAdapter();
        recyclerView.setAdapter(adapter);

        biodataViewModel = ViewModelProviders.of(this).get(BiodataViewModel.class);
        biodataViewModel.getAll().observe(getViewLifecycleOwner(), new Observer<List<Biodata>>() {
            @Override
            public void onChanged(List<Biodata> biodata) {
                biodataList.clear();
                for(int i = 0; i < biodata.size(); i++) {
                    if(biodata.get(i).getEmail().contains(email))
                        biodataList.add(biodata.get(i));
                }
                adapter.setBiodata(biodataList);
            }
        });



        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {

            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                biodataViewModel.delete(adapter.getBiodataAt(viewHolder.getAdapterPosition()));
                Toast.makeText(getContext(), "biodata deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new BiodataAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Biodata biodata) {
                Toast.makeText(getContext(), "Update biodata", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), BiodataAddActivity.class);
                intent.putExtra(BiodataAddActivity.EXTRA_ID, biodata.getId());
                intent.putExtra(BiodataAddActivity.EXTRA_MASS, biodata.getMass());
                startActivityForResult(intent, EDIT_BIODATA_REQUEST);
            }
        });

        return root;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_BIODATA_REQUEST && resultCode == RESULT_OK) {
            String date = data.getStringExtra(BiodataAddActivity.EXTRA_DATE);
            int mass = data.getIntExtra(BiodataAddActivity.EXTRA_MASS, 1);
            int height = data.getIntExtra(BiodataAddActivity.EXTRA_HEIGHT, 1);

            Biodata biodata = new Biodata(date, mass, height, email);
            biodataViewModel.insert(biodata);
            Toast.makeText(getContext(), "Biodata saved", Toast.LENGTH_SHORT).show();
        } else if (requestCode == EDIT_BIODATA_REQUEST && resultCode == RESULT_OK) {
            int id = data.getIntExtra(BiodataAddActivity.EXTRA_ID, -1);

            if (id == -1) {
                Toast.makeText(getContext(), "Biodata can't be updated", Toast.LENGTH_SHORT).show();
                return;
            }

            String date = data.getStringExtra(BiodataAddActivity.EXTRA_DATE);
            int mass = data.getIntExtra(BiodataAddActivity.EXTRA_MASS, 1);
            int height = data.getIntExtra(BiodataAddActivity.EXTRA_HEIGHT, 1);

            Biodata biodata = new Biodata(date, mass, height, email);
            biodata.setId(id);
            biodataViewModel.update(biodata);

            Toast.makeText(getContext(), "Biodata updated", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getContext(), "Biodata can't be saved", Toast.LENGTH_SHORT).show();
        }
    }
}
