package com.example.tong.mathrocks_v3.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper{

    public static final String DB_FILE_NAME = "mathrocks_db";
    public static final Integer DB_VERSION = 1;

    public DBHelper(Context context){super(context,DB_FILE_NAME,null,DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(MathRocksDatabaseTables.SQL_CREATE_MATHTESTS_TABLE);
        db.execSQL(MathRocksDatabaseTables.SQL_CREATE_QUESTIONS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

        db.execSQL(MathRocksDatabaseTables.SQL_DELETE_MATHTESTS_TABLE);
        db.execSQL(MathRocksDatabaseTables.SQL_DELETE_QUESTIONS_TABLE);

        onCreate(db);
    }
}
