package com.example.a8gate;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class habit extends AppCompatActivity {

    RecyclerView recycleViewHabit;
    FloatingActionButton add_weight,bmical;


    DatabaseHelper myDB;
    ArrayList<String> w_id, date, w_weight;
    CustomAdapterHabit customAdapterHabit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_habit);


        //Initialize And Assign Variable

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.habit);

        //Perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                ,MainActivity.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.habit:
                        return true;

                    case R.id.body:
                        startActivity(new Intent(getApplicationContext()
                                ,body.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.meal:
                        startActivity(new Intent(getApplicationContext()
                                ,meal.class));
                        overridePendingTransition(0,0);
                        return true;
                }
                return false;
            }
        });



        ///////////////////////////////////////////////





////////////////////////////////////////////////////////////////////////



        recycleViewHabit = findViewById(R.id.recycleViewHabit);
        add_weight = findViewById(R.id.add_weight);
        bmical = findViewById(R.id.bmical);

        add_weight.setOnClickListener(view -> {
            Intent intent = new Intent(habit.this, addweight.class);
            startActivity(intent);

        });


        bmical = findViewById(R.id.bmical);

        bmical.setOnClickListener(view -> {
            Intent intent = new Intent(habit.this, bmical.class);
            startActivity(intent);

        });


        myDB = new DatabaseHelper(habit.this);
        w_id = new ArrayList<>();
        date = new ArrayList<>();
        w_weight = new ArrayList<>();

        storeDataInArrays();

        customAdapterHabit = new CustomAdapterHabit(habit.this,this, w_id, date, w_weight);
        recycleViewHabit.setAdapter(customAdapterHabit);

        recycleViewHabit.setLayoutManager(new LinearLayoutManager(habit.this));
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllDataW();
        if (cursor.getCount() == 0) {
            Toast.makeText(this, "No Data", Toast.LENGTH_SHORT).show();
        } else {
            while (cursor.moveToNext()) {
                w_id.add(cursor.getString(0));
                date.add(cursor.getString(1));
                w_weight.add(cursor.getString(2));
            }
        }
    }









}




