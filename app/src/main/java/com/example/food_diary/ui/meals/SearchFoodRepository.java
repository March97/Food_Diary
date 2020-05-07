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
    private MutableLiveData<Map<String,Object> >combo = new MutableLiveData<>();


    public SearchFoodRepository(String input) {

        if (listItems == null) {
            listItems = new ArrayList<>();
        }


    input= "Se";

        FirebaseDatabase database = FirebaseDatabase.getInstance();

        DatabaseReference myRef = database.getReference()
                ;


        Query query = myRef.orderByChild("product_name").startAt(input).endAt(input +"\uf8ff").limitToFirst(10);

        System.out.println("LOOOL");
        query.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                int i=0;
                for(DataSnapshot ds : dataSnapshot.getChildren()) {
                    i++;

                    Map<String, Object> map = (Map<String, Object>) ds.getValue();
                    combo.setValue(map);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                String likes = databaseError.getDetails();
                System.out.println("11111" + likes);
            }
        });
    }



    public MutableLiveData<Map<String,Object> > getItem() {

        return combo;
    }




}
