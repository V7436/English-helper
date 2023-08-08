package com.example.dictionary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

class MyDatabaseHelper extends SQLiteOpenHelper {
    private Context context;
    private static final String DATABASE_NAME = "Dictionary";
    private static final int DATABASE_VERSION = 1;

    public static final String TABLE_NAME = "Words";
    public static final String KEY_ID = "_id";
    public static final String KEY_EN = "EN";
    public static final String KEY_RUS1 = "RUS1";
    public static final String KEY_RUS2 = "RUS2";
    public static final String KEY_RUS3 = "RUS3";
    public static final String KEY_PRIORITY = "PRIORITY";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = ("create table " + TABLE_NAME + " ("
                + KEY_ID + " integer primary key, "
                + KEY_EN + " text, "
                + KEY_RUS1 + " text, "
                + KEY_RUS2 + " text, "
                + KEY_RUS3 + " text, "
                + KEY_PRIORITY + " text)");
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists " + TABLE_NAME);
        onCreate(db);
    }
    Cursor addWord(String en, String rus1, String rus2, String rus3, String prior){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_EN, en);
        cv.put(KEY_RUS1, rus1);
        cv.put(KEY_RUS2, rus2);
        cv.put(KEY_RUS3, rus3);
        cv.put(KEY_PRIORITY, prior);

        long result = db.insert(TABLE_NAME, null, cv);

        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added successfully", Toast.LENGTH_SHORT).show();
        }
        return null;
    }
    Cursor readAllData(){
        String query = "select * from " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }
    void updateData(String row_id, String en, String rus1, String rus2, String rus3, String prior){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(KEY_EN, en);
        cv.put(KEY_RUS1, rus1);
        cv.put(KEY_RUS2, rus2);
        cv.put(KEY_RUS3, rus3);
        cv.put(KEY_PRIORITY, prior);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Update", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Success!", Toast.LENGTH_SHORT).show();
        }
    }
    void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if (result == -1) {
            Toast.makeText(context, "Fail", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Deleted", Toast.LENGTH_SHORT).show();
        }
    }
}