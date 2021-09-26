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

public class body extends AppCompatActivity {
    BodyDatabaseHelper myDbb;
    EditText editBodyPartName,editMeasurement,editDate,editTextId;
    Button btnAddData;
    Button btnviewAll;
    Button btnviewUpdate;
    Button btnBodyDelete;
    Button btnViewLeftBicep;
    Button btnViewRightBicep;
    Button btnViewChest, btnViewWaist, btnViewHips, btnViewLeftThigh, btnViewRightThigh, btnViewLeftCalve, btnViewRightCalve, btnBodyMassCal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_body);

        myDbb = new BodyDatabaseHelper(this);

        editBodyPartName = findViewById(R.id.editText_body);
        editMeasurement = findViewById(R.id.editText_measurement);
        editDate = findViewById(R.id.editText_date);
        editTextId = findViewById(R.id.editText_id);
        btnAddData = findViewById(R.id.button_add);
        btnviewAll = findViewById(R.id.button_viewAll);
        btnviewUpdate = findViewById(R.id.button_update);
        btnBodyDelete = findViewById(R.id.button_delete);
        btnViewLeftBicep = findViewById(R.id.view_left_biceps);
        btnViewRightBicep = findViewById(R.id.view_right_biceps);
        btnViewChest = findViewById(R.id.view_chest);
        btnViewWaist = findViewById(R.id.view_waist);
        btnViewHips = findViewById(R.id.view_hips);
        btnViewLeftThigh = findViewById(R.id.view_left_thigh);
        btnViewRightThigh = findViewById(R.id.view_right_thigh);
        btnViewLeftCalve = findViewById(R.id.view_left_calve);
        btnViewRightCalve = findViewById(R.id.view_right_calve);


        BodyAddData();
        BodyviewAll();
        BodyUpdateData();
        BodyDeleteData();
        BodyViewAllLeftBicep();
        BodyViewAllRightBicep();
        BodyViewAllChest();
        BodyViewAllWaist();
        BodyViewAllHips();
        BodyViewAllLeftThigh();
        BodyViewAllRightThigh();
        BodyViewAllLeftCalve();
        BodyViewAllRightCalve();

        //Initialize And Assign Variable

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_nav);

        //Set Home Selected
        bottomNavigationView.setSelectedItemId(R.id.body);

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
                        startActivity(new Intent(getApplicationContext()
                                ,habit.class));
                        overridePendingTransition(0,0);
                        return true;

                    case R.id.body:
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

            btnBodyMassCal = findViewById(R.id.view_lean_body_mass);
        btnBodyMassCal.setOnClickListener(view -> {
             Intent intent = new Intent(body.this, LeanBodyMassCalculator.class);
             startActivity(intent);
        });
    }



    public void BodyDeleteData() {
        btnBodyDelete.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Integer deletedRows = myDbb.deleteBodyData(editTextId.getText().toString());
                        if(deletedRows > 0)
                            Toast.makeText(body.this,"Data Deleted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(body.this,"Data not Deleted",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void BodyUpdateData() {
        btnviewUpdate.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isUpdated = myDbb.updateBodyData(editTextId.getText().toString(),editBodyPartName.getText().toString(),
                                editMeasurement.getText().toString(),editDate.getText().toString());
                        if(isUpdated == true)
                            Toast.makeText(body.this,"Data Updated",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(body.this,"Data not Updated",Toast.LENGTH_LONG).show();

                    }
                }
        );
    }

    public void BodyAddData(){
        btnAddData.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDbb.insertBodyData(editBodyPartName.getText().toString(),
                                editMeasurement.getText().toString(),
                                editDate.getText().toString() );
                        if(isInserted == true)
                            Toast.makeText(body.this,"Data Inserted",Toast.LENGTH_LONG).show();
                        else
                            Toast.makeText(body.this,"Data not Inserted",Toast.LENGTH_LONG).show();
                    }
                }
        );
    }

    public void BodyviewAll() {
        btnviewAll.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("All Details",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllLeftBicep() {
        btnViewLeftBicep.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getLeftBicepAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Left Bicep",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllRightBicep() {
        btnViewRightBicep.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getRightBicepAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }
    public void BodyViewAllChest() {
        btnViewChest.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getChestAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllWaist() {
        btnViewWaist.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getWaistAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllHips() {
        btnViewHips.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getHipsAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllLeftThigh() {
        btnViewLeftThigh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getLeftThighAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllRightThigh() {
        btnViewRightThigh.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getRightThighAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllLeftCalve() {
        btnViewLeftCalve.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getLeftCalveAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
                        }

                        //show all data
                        showMessage("Data",buffer.toString());
                    }
                }
        );
    }

    public void BodyViewAllRightCalve() {
        btnViewRightCalve.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Cursor res = myDbb.getRightCalveAllData();
                        if(res.getCount() == 0) {
                            //show message
                            showMessage("Error","Nothing found on the database");
                            return;
                        }

                        StringBuffer buffer = new StringBuffer();
                        while (res.moveToNext()) {
                            buffer.append("Id :"+ res.getString(0)+"\n");
                            buffer.append("Body Part Name :"+ res.getString(1)+"\n");
                            buffer.append("Measurement in CM:"+ res.getString(2)+"\n");
                            buffer.append("Date :"+ res.getString(3)+"\n\n");
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