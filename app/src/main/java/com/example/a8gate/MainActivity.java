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

public class MainActivity extends AppCompatActivity {
    ExeDatabaseHelper myDDb;
    EditText editNamee,editRepss,editTurnss,editTextid;
    Button btnAddDataa;
    Button btnviewAlll,cal_c;
    Button btnupdate;
    Button btndelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDDb = new ExeDatabaseHelper(this);

        editNamee = (EditText)findViewById(R.id.editTextName2);
        editRepss = (EditText)findViewById(R.id.editTextName4);
        editTurnss = (EditText)findViewById(R.id.editTextName3);
        editTextid = (EditText)findViewById(R.id.editTextName1);
        btnAddDataa = (Button) findViewById(R.id.button_add);
        btnviewAlll = (Button) findViewById(R.id.button_view);
        btnupdate = (Button) findViewById(R.id.button_update);
        btndelete = (Button) findViewById(R.id.button_delete);

        insertData();
        UpdateData();
        viewAll();
        DeleteData();

        //Initialize And Assign Variable

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.home);

        //Perform itemSelectedListener
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch (menuItem.getItemId()) {
                    case R.id.home:
                        return true;

                    case R.id.habit:
                        startActivity(new Intent(getApplicationContext()
                                ,habit.class));
                        overridePendingTransition(0,0);
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

        cal_c = findViewById(R.id.cal_c);
        cal_c.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, Calorie_calculator.class);
            startActivity(intent);
        });


    }

    public void DeleteData() {
        btndelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedRows = myDDb.deleteData(editTextid.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(MainActivity.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Deleted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }




    public void UpdateData() {
        btnupdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDDb.updateData(editTextid.getText().toString(),
                                editNamee.getText() .toString(),
                                editRepss.getText().toString(),editTurnss.getText().toString());
                        if(isUpdate == true)
                            Toast.makeText(MainActivity.this,"Data Update",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data not Updated",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
    public void insertData() {
        btnAddDataa.setOnClickListener(
                new View.OnClickListener() {

                    @Override
                    public void onClick(View v) {
                        boolean isInserted = myDDb.insertData(editNamee.getText() .toString() ,
                                editRepss.getText() .toString() ,
                                editTurnss.getText() .toString() );
                        if (isInserted = true)
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(MainActivity.this,"Data Inserted",Toast.LENGTH_LONG).show();


                    }
                }
        );


    }
    public void viewAll() {
        btnviewAlll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Cursor res = myDDb.getAllData();
                        if(res.getCount() == 0) {
                            // show message
                            showMessage("Error","Nothing found");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Name :"+ res.getString(1)+"\n");
                            buffer.append("Reps :"+ res.getString(2)+"\n");
                            buffer.append("Turns :"+ res.getString(3)+"\n\n");
                        }

                        // Show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }

}