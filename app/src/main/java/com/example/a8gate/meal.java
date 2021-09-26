package com.example.a8gate;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class meal extends AppCompatActivity {
    MealDatabaseHelper myDbmm;
    EditText editPcount,editMtype,editMeal ,editTextId;
    Button btnAddMeal;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnDelete;
    Button pCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_meal);
        myDbmm = new MealDatabaseHelper(this);
        editTextId = findViewById(R.id.meal_id);
        editPcount = findViewById(R.id.countbtnA);
        editMtype = findViewById(R.id.MealtypeA);
        editMeal = findViewById(R.id.MealA);

        btnAddMeal = findViewById(R.id.butttonA);
        btnviewAll = findViewById(R.id.button2);
        btnviewUpdate = findViewById(R.id.button3);
        btnDelete = findViewById(R.id.button4);
        AddData();
        viewAll();
        updateData();
        DeleteData();


        //Initialize And Assign Variable

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.meal);

        //Perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        startActivity(new Intent(getApplicationContext()
                                , MainActivity.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.habit:
                        startActivity(new Intent(getApplicationContext()
                                , habit.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.body:
                        startActivity(new Intent(getApplicationContext()
                                , body.class));
                        overridePendingTransition(0, 0);
                        return true;

                    case R.id.meal:
                        return true;
                }
                return false;
            }
        });

        pCal = findViewById(R.id.pCal);
        pCal.setOnClickListener(view -> {
            Intent intent = new Intent(meal.this, ProteinCal.class);
            startActivity(intent);

        });

    }
    public void DeleteData() {
        btnDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDbmm.deleteData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(meal.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(meal.this, "Data Not Deleted", Toast.LENGTH_SHORT).show();
                    }
                }
        );
    }
public void updateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdate = myDbmm.updateData(editTextId.getText().toString() ,
                                editPcount.getText().toString(),
                                editMtype.getText().toString() ,editMeal.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(meal.this, "Data Update", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(meal.this, "Data Not Updated", Toast.LENGTH_SHORT).show();
                    }
                }
        );
}
public void AddData() {
    btnAddMeal.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View vie) {
                   boolean isInserted = myDbmm.AddData(editPcount.getText().toString(),
                            editMtype.getText().toString(),
                            editMeal.getText().toString() );
                   if(isInserted == true)
                       Toast.makeText(meal.this, "Data Inserted", Toast.LENGTH_SHORT).show();

                   else
                       Toast.makeText(meal.this, "Data Not Inserted", Toast.LENGTH_SHORT).show();

                }
            }
    );
}
public void viewAll(){
    btnviewAll.setOnClickListener(
            new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                   Cursor res = myDbmm.getAllData();
                   if(res.getCount() == 0) {
                       //show message
                       showMessage("Error", "Nothing found");
                       return;
                   }

                   StringBuffer buffer = new StringBuffer();
                   while (res.moveToNext()){
                       buffer.append("Id :"+ res.getString(0)+"\n");
                       buffer.append("Protean_count :"+ res.getString(1)+"\n");
                       buffer.append("Meal_type :"+ res.getString(2)+"\n");
                       buffer.append("Meal :"+ res.getString(3)+"\n\n");
                   }

                   //show all data
                    showMessage("Data",buffer.toString());
                }
            }
    );
}
public void showMessage(String title,String Message) {
    AlertDialog.Builder builder = new AlertDialog.Builder(this);
    builder.setCancelable(true);
    builder.setTitle(title);
    builder.setMessage(Message);
    builder.show();
}



}