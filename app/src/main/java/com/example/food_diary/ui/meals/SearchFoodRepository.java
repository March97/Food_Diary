package com.example.food_diary.ui.meals;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.util.Log;
import android.util.Pair;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

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

import static android.content.ContentValues.TAG;


public class SearchFoodRepository {


    private ArrayList<String> listItems = new ArrayList<>();
    private ArrayList<HashMap<String, String>> listItemsMap = new ArrayList<>();
    private Pair<ArrayList<String>, ArrayList<HashMap<String, String>>> c1;
    private MutableLiveData<ArrayList<String>> combo = new MutableLiveData<>();


    public SearchFoodRepository(String input) {

        if (listItems == null) {
            listItems = new ArrayList<>();
        }

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("jedzenie/Q2orj3h0bzifU2uBOQ8j");
        System.out.println(myRef.child("115").child("product_name"));
        System.out.println("2" + myRef.orderByChild("product_name")
                .startAt("Ser")
                .endAt("Ser"+"\uf8ff"));


        myRef.child("115").child("product_name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });


    }

    public MutableLiveData<ArrayList<String>> update(String input) {
     //   new Show_prod(input).execute();
        return combo;
    }

    public MutableLiveData<ArrayList<String>> getItem() {

        return combo;
    }



/*

    class Show_prod extends AsyncTask<Void, Void, String> {
        private String input;

        public Show_prod(String input) {
            this.input = input;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            //this method will be running on UI thread

            listItems.clear();


        }

        @Override
        protected String doInBackground(Void... voids) {

            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            super.onPostExecute(s);
            System.out.println(s);

            try {
                //Converting response to JSON Object
                JSONObject object = new JSONObject(s);
                JSONArray arr = object.getJSONArray("data");
                listItemsMap.clear();
                listItems.clear();
                System.out.println("oko: " + s.length());
                if (!object.getBoolean("error")) {
                    for (int i = 0; i < 10; i++) {

                        //if no error in response
                        JSONObject obj = arr.getJSONObject(i);
                        String biodata = "product_name: " + obj.getString("product_name") + "\nquantity: " + obj.getString("serving_quantity") + "\nenergy: " + obj.getInt("energy");
                        // System.out.println(biodata);
                        listItems.add(biodata);
                        HashMap<String, String> tmp = new HashMap<String, String>();

                        tmp.put("product_name", obj.getString("product_name"));
                        tmp.put("quantity", obj.getString("serving_quantity"));
                        tmp.put("energy", obj.getString("energy"));
                        tmp.put("fat", obj.getString("fat"));
                        tmp.put("carbs", obj.getString("carbs"));
                        tmp.put("proteins", obj.getString("proteins"));
                        listItemsMap.add(tmp);


                    }
                    System.out.println("jajko " + listItemsMap.toString());
                    c1 = new Pair<>(listItems, listItemsMap);
                    combo.setValue(c1);


                }
            } catch (Exception e) {
                e.printStackTrace();
                c1 = new Pair<>(listItems, listItemsMap);
                combo.setValue(c1);
            }


        }
    }
*/


}
