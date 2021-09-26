package com.example.a8gate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class LeanBodyMassCalculator extends AppCompatActivity {

    TextView result;
    EditText edtWeight, edtHeight;
    String BodyLean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lean_body_mass_calculator);

        result = findViewById(R.id.lean_body_result);
        edtWeight = findViewById(R.id.lWeight);
        edtHeight = findViewById(R.id.lHeight);
    }

    public void calculateLean(View view) {
        String height = edtHeight.getText().toString();
        String weight = edtWeight.getText().toString();

        float hv = Float.parseFloat(height);
        float wv = Float.parseFloat(weight);
        float lean = (float) ((0.32810 * wv) + (0.33929 * hv) - 29.5336);

        BodyLean = ""+lean;
        result.setText(BodyLean);
    }
}