package com.example.a8gate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Calorie_calculator extends AppCompatActivity {
    TextView res;
    EditText calories,reps,turns;
    Button cal;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_calculator);

        res=findViewById(R.id.res);
        calories=findViewById(R.id.calp);
        reps=findViewById(R.id.reps);
        turns=findViewById(R.id.sets);
        cal=findViewById(R.id.calculate);

    }
    public void calculateC(View view){
        String c=calories.getText().toString();
        String r=reps.getText().toString();
        String t=turns.getText().toString();
        float cw=Float.parseFloat(c);
        float rw=Float.parseFloat(r);
        float tw=Float.parseFloat(t);

        float z=cw*rw*tw;
        x=""+z;
        res.setText(x);


    }
}