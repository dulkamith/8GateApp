package com.example.a8gate;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class MealDatabaseHelper extends SQLiteOpenHelper {
    public static final String MEAL_DATABASE_NAME="Meal.db";
    public static final String MEAL_TABLE_NAME="meal_table";
    public static final String COLL_1="ID";
    public static final String COLL_2="PROTEAN_COUNT";
    public static final String COLL_3="MEAL_TYPE";
    public static final String COLL_4="MEAL";

    public MealDatabaseHelper(@Nullable Context context) {
        super(context, MEAL_DATABASE_NAME, null, 1);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table " + MEAL_TABLE_NAME +" (ID INTEGER PRIMARY KEY AUTOINCREMENT,PROTEAN_COUNT TEXT,MEAL_TYPE TEXT,MEAL TEXT)");


    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+MEAL_TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public boolean AddData(String pcount,String mtype,String mname){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLL_2,pcount);
        contentValues.put(COLL_3,mtype);
        contentValues.put(COLL_4,mname);
        long result = sqLiteDatabase.insert(MEAL_TABLE_NAME,null ,contentValues);
        if(result == -1)
            return  false;
        else
            return true;
    }
    public Cursor getAllData(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        Cursor res = sqLiteDatabase.rawQuery("select * from "+MEAL_TABLE_NAME,null);
        return res;
    }

    public boolean updateData(String id,String pcount,String mtype,String mname){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COLL_1,id);
        contentValues.put(COLL_2,pcount);
        contentValues.put(COLL_3,mtype);
        contentValues.put(COLL_4,mname);
        sqLiteDatabase.update(MEAL_TABLE_NAME, contentValues, "ID = ?",new String[] {id});
        return true;
    }
    public  Integer deleteData (String id) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        return sqLiteDatabase.delete(MEAL_TABLE_NAME, "ID = ?",new String[] {id});
    }
}
