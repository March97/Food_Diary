package com.example.food_diary.ui.meals;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.MutableLiveData;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.content.ContentValues.TAG;


public class SearchFoodRepository {


    private ArrayList<String> listItems = new ArrayList<>();
    private MutableLiveData<Map<String, Object>> combo = new MutableLiveData<>();


    public SearchFoodRepository(String input) {

        if (listItems == null) {
            listItems = new ArrayList<>();
        }


        if (input == null) {
            input = "s";
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference();


        Query query = myRef.orderByChild("product_name").startAt(input).endAt(input + "\uf8ff").limitToFirst(10);

        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    i++;
                    String name = ds.child("product_name").getValue(String.class);
                    Integer energy = ds.child("energy_100g").getValue(Integer.class);
                    Double carbohydrates = (Double) ds.child("carbohydrates_100g").getValue(Double.class);
                    Double proteins = ds.child("proteins_100g").getValue(Double.class);
                    Double fat = ds.child("fat_100g").getValue(Double.class);

                    Map<String, Object> map2 = new HashMap<>();
                    map2.put("product_name", name);
                    map2.put("energy_100g", energy);
                    map2.put("carbohydrates_100g", carbohydrates);
                    map2.put("proteins_100g", proteins);
                    map2.put("fat_100g", fat);

                    System.out.println("tag" + map2.toString());
                    Log.d("skom", map2.toString());
                    combo.setValue(map2);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String likes = databaseError.getDetails();
                System.out.println("11111" + likes);
            }
        });
    }


    public MutableLiveData<Map<String, Object>> getItem() {
        System.out.println(combo.toString());
        return combo;
    }


}
