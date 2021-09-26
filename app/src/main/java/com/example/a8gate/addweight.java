package com.example.a8gate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class addweight extends AppCompatActivity {

    EditText w_date,weight_input;
    Button save_weight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addweight);

        w_date = findViewById(R.id.w_date);
        weight_input = findViewById(R.id.weight_input);
        save_weight = findViewById(R.id.save_weight);

        save_weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseHelper myDB = new DatabaseHelper(addweight.this);
                myDB.addweight(w_date.getText().toString().trim(),
                weight_input.getText().toString().trim());


            }
        });


    }
}