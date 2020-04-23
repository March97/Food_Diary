package com.example.food_diary.ui.biodata;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.ArrayList;
import java.util.List;

import static android.app.Activity.RESULT_OK;

public class BiodataFragment extends Fragment {

//    private BiodataViewModel biodataViewModel;
//
//    @Nullable
//    @Override
//    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        View root = inflater.inflate(R.layout.fragment_biodata_list, container, false);
//
//        biodataViewModel = ViewModelProviders.of(this).get(BiodataViewModel.class);
//
//        RecyclerView recyclerView = root.findViewById(R.id.biodata_recyclerview);
//        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
//        recyclerView.setHasFixedSize(true);
//
//        final BiodataAdapter adapter = new BiodataAdapter();
//        recyclerView.setAdapter(adapter);
//
//        biodataViewModel.getAll().observe(this, new Observer<List<Biodata>>() {
//            @Override
//            public void onChanged(List<Biodata> biodata) {
//                adapter.setBiodata(biodata);
//                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        return root;
//    }

    public static final int ADD_BIODATA_REQUEST = 1;
    public static final int EDIT_BIODATA_REQUEST = 2;

    private BiodataViewModel biodataViewModel;
    private ArrayList<Biodata> biodataList;
    //SharedPreferences sharedPreferences;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_biodata_list, container, false);
        FloatingActionButton buttonAddOrder = root.findViewById(R.id.biodata_fab);
        buttonAddOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
//                Intent intent = new Intent(getContext(), AddbiodataActivity.class);
//                startActivityForResult(intent, ADD_biodata_REQUEST);
            }
        });

        RecyclerView recyclerView = root.findViewById(R.id.biodata_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setHasFixedSize(true);

        final BiodataAdapter adapter = new BiodataAdapter();
        recyclerView.setAdapter(adapter);

        biodataViewModel = ViewModelProviders.of(this).get(BiodataViewModel.class);
        biodataViewModel.getAll().observe(this, new Observer<List<Biodata>>() {
            @Override
            public void onChanged(List<Biodata> biodata) {
                adapter.setBiodata(biodata);
                //sharedPreferences = getActivity().getSharedPreferences("biodata_NAME", Context.MODE_PRIVATE);
                //SharedPreferences.Editor editor = sharedPreferences.edit();
                biodataList = (ArrayList) biodata;
//                editor.putInt("arraySize", biodataList.size());
//                for(int i = 0; i < biodataesList.size(); i++) {
//                    String keyName = "D ";
//                    String keyPrice = "P ";
//                    keyName += i;
//                    keyPrice += i;
//                    System.out.println(keyName);
//                    editor.putString(keyName, biodataesList.get(i).getName());
//                    editor.putString(keyPrice, String.valueOf(biodataesList.get(i).getPrice()));
//                    editor.commit();
//                }

                Toast.makeText(getContext(), "onChanged", Toast.LENGTH_SHORT).show();
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
                Toast.makeText(getContext(), "CLICKED", Toast.LENGTH_SHORT).show();
//                Intent intent = new Intent(getContext(), AddbiodataActivity.class);
//                intent.putExtra(AddbiodataActivity.EXTRA_ID, biodata.getId());
//                intent.putExtra(AddbiodataActivity.EXTRA_NAME, biodata.getName());
//                intent.putExtra(AddbiodataActivity.EXTRA_PRICE, biodata.getPrice());
//                intent.putExtra(AddbiodataActivity.EXTRA_MASS, biodata.getMass());
//                startActivityForResult(intent, EDIT_biodata_REQUEST);
            }
        });

        return root;
    }

//    public void onActivityResult(int requestCode, int resultCode, Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == ADD_biodata_REQUEST && resultCode == RESULT_OK) {
//            String name = data.getStringExtra(AddbiodataActivity.EXTRA_NAME);
//            int mass = data.getIntExtra(AddbiodataActivity.EXTRA_MASS, 1);
//            double price = data.getDoubleExtra(AddbiodataActivity.EXTRA_PRICE, 1);
//
//            biodata biodata = new biodata(name, price, mass);
//            biodataViewModel.insert(biodata);
//            Toast.makeText(getContext(), "biodata saved", Toast.LENGTH_SHORT).show();
//        } else if (requestCode == EDIT_biodata_REQUEST && resultCode == RESULT_OK) {
//            int id = data.getIntExtra(AddbiodataActivity.EXTRA_ID, -1);
//
//            if (id == -1) {
//                Toast.makeText(getContext(), "biodata can't be updated", Toast.LENGTH_SHORT).show();
//                return;
//            }
//
//            String name = data.getStringExtra(AddbiodataActivity.EXTRA_NAME);
//            int mass = data.getIntExtra(AddbiodataActivity.EXTRA_MASS, 1);
//            double price = data.getDoubleExtra(AddbiodataActivity.EXTRA_PRICE, 1);
//
//            biodata biodata = new biodata(name, price, mass);
//            biodata.setId(id);
//            biodataViewModel.update(biodata);
//
//            Toast.makeText(getContext(), "biodata updated", Toast.LENGTH_SHORT).show();
//        } else {
//            Toast.makeText(getContext(), "biodata can't be saved", Toast.LENGTH_SHORT).show();
//        }
//    }
}
