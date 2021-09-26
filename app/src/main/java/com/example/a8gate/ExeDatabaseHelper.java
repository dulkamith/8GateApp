package com.example.a8gate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class ExeDatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAMEE = "workout.db";
    public static final String TABLE_NAMEE = "workout_table";
    public static final String EXECOL_1 = "EID";
    public static final String EXECOL_2 = "Name";
    public static final String EXECOL_3 = "Reps";
    public static final String EXECOL_4 = "Turns";


    public ExeDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAMEE, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase EXEdb) {
        EXEdb.execSQL("create table " + TABLE_NAMEE +" (EID INTEGER PRIMARY KEY AUTOINCREMENT,Name TEXT,Reps INTEGER,Turns INTEGER)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase EXEdb, int i, int i1) {

        EXEdb.execSQL("DROP TABLE IF EXISTS "+TABLE_NAMEE);
        onCreate(EXEdb);

    }
    public boolean insertData(String name,String reps,String turns) {
        SQLiteDatabase EXEdb = this.getWritableDatabase();
        ContentValues contentV = new ContentValues();
        contentV.put(EXECOL_2,name);
        contentV.put(EXECOL_3,reps);
        contentV.put(EXECOL_4,turns);
        long result = EXEdb.insert(TABLE_NAMEE,null ,contentV);
        if(result == -1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAMEE,null);
        return res;
    }
    public boolean updateData(String id,String name,String reps,String turns) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(EXECOL_1,id);
        contentValues.put(EXECOL_2,name);
        contentValues.put(EXECOL_3,reps);
        contentValues.put(EXECOL_4,turns);
        db.update(TABLE_NAMEE, contentValues, "EID = ?",new String[] { id });
        return true;
    }
    public Integer deleteData (String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAMEE, "EID = ?",new String[] {id});
    }
}

