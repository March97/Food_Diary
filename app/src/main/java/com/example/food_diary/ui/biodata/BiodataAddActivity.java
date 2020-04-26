package com.example.food_diary.ui.biodata;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.food_diary.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class BiodataAddActivity extends AppCompatActivity {

    public static final String EXTRA_ID =
            "com.example.bondyra.ui.dishes.EXTRA_ID";

    public static final String EXTRA_MASS =
            "com.example.bondyra.ui.dishes.EXTRA_MASS";

    public static final String EXTRA_DATE =
            "com.example.bondyra.ui.dishes.EXTRA_PRICE";

    private EditText mass_et;
    private TextView date_tv;
    private Button ok_btn;
    private DatePickerDialog.OnDateSetListener dateSetListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_biodata_add);

        mass_et = findViewById(R.id.biodata_add_mass);
        date_tv = findViewById(R.id.biodata_add_date);
        ok_btn = findViewById(R.id.biodata_add_button);

        Intent intent = getIntent();

        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit biodata");
            mass_et.setText(String.valueOf(intent.getIntExtra(EXTRA_MASS, 1)));
            date_tv.setText(intent.getStringExtra(EXTRA_DATE));
        } else
        {
            setTitle("Add biodata");
        }

        ok_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveBiodata();
            }
        });

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
                String date = year + "-" + month + "-" + dayOfMonth;
                date = sdf.format(dateCal);
                date_tv.setText(date);
            }
        };
    }

    private void saveBiodata() {
        int mass = Integer.valueOf(mass_et.getText().toString());
        String date = date_tv.getText().toString();

        if(date.trim().isEmpty() || mass <= 0) {
            Toast.makeText(this, "Please insert proper values", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_MASS, mass);
        data.putExtra(EXTRA_DATE, date);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        if (id != -1) {
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }
}
