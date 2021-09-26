package com.example.a8gate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ProteinCal extends AppCompatActivity {

    TextView pResult;
    EditText pWeight;
    Button pCal;
    String pror;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_protein_cal);

        pResult= findViewById(R.id.pResult);
        pWeight =findViewById(R.id.Pweight);
        pCal = findViewById(R.id.pCal);
    }
    public void calcPro(View view){
        String pw = pWeight.getText().toString();

        float prowe = Float.parseFloat(pw)*(float) 2.205;

        float pcount = prowe* (float)0.36;

        pror = ""+pcount;


        pResult.setText(pror);

    }
}