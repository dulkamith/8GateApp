package com.example.a8gate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class bmical extends AppCompatActivity {

    TextView result,bmiresult;
    EditText weight,height;
    Button btn_bmi;
    String calc,BMIresult,res;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmical);

        weight = findViewById(R.id.weight);
        height = findViewById(R.id.height);
        btn_bmi = findViewById(R.id.btn_bmi);
        result = findViewById(R.id.result);
        bmiresult = findViewById(R.id.bmiresult);

    }
     public void calculateBMI(View view){
        String w = weight.getText().toString();
        String h = height.getText().toString();

        float weightValue = Float.parseFloat(w);
        float heightValue = Float.parseFloat(h)/100;

        float bmi = weightValue/(heightValue*heightValue);

        if(bmi < 16){
            BMIresult="Severely Under Weight";
        }
        else if(bmi >= 16 && bmi <=18.5){
            BMIresult="Under Weight";
        }
        else if(bmi > 18.5 && bmi <=25){
            BMIresult="Healthy";
        }
        else if(bmi > 25 && bmi <=29.9){
            BMIresult="Over Weight";
        }
        else{
            BMIresult="Obese";
        }

        calc ="" + bmi + "\n";
        res =""+BMIresult;

        result.setText(calc);
        bmiresult.setText(res);

    }
}