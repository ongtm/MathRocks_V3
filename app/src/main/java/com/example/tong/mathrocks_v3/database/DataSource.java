package com.example.tong.mathrocks_v3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;


import com.example.tong.mathrocks_v3.model.MathTest;

public class DataSource {
    private Context mContext;
    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;

    public DataSource(Context context){
        this.mContext = context;
        mDbHelper= new DBHelper(mContext);
        mDatabase = mDbHelper.getWritableDatabase();
    }


    public void open(){
        mDatabase = mDbHelper.getWritableDatabase();
    }

    public void close(){
        mDbHelper.close();
    }


    public void onInsert(ContentValues contentValues, String tableName){

        mDatabase.insertOrThrow(tableName,null,contentValues);

    }

    public boolean isEmpty(String tableName) {
        mDatabase = mDbHelper.getWritableDatabase();

        long numOfRows = DatabaseUtils.queryNumEntries(mDatabase, tableName);

        if (numOfRows == 0) {
            return true;
        } else {
            return false;
        }

    }

    public long getRecordCount(String tableName){
        mDatabase = mDbHelper.getReadableDatabase();

        long numOfRows = DatabaseUtils.queryNumEntries(mDatabase,tableName);
        return numOfRows;
    }

}
