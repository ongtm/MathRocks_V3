package com.example.tong.mathrocks_v3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.widget.Toast;


import com.example.tong.mathrocks_v3.model.MathTest;
import com.example.tong.mathrocks_v3.model.Question;

import java.util.ArrayList;

import static com.example.tong.mathrocks_v3.database.MathRocksDatabaseTables.TABLE_QUESTIONS;

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

    public ArrayList<MathTest> getMathTests() {
        mDatabase = mDbHelper.getReadableDatabase();

        Cursor cursor = mDatabase.query("tblMathTests", new String[]{"tblMathTests.column_testID, " +
                "tblMathTests.column_testLevel, tblMathTests.column_testType, tblMathTests.column_totalQuestions, " +
                "tblMathTests.column_totalCorrectQuestions, tblMathTests.column_totalIncorrectQuestions, tblMathTests.column_testScore, tblMathTests.column_testDate"},null,null,null,null,null);

        ArrayList<MathTest> mathTests = new ArrayList<>();

      if (cursor != null) {
            cursor.moveToFirst();

            do {
                MathTest mathTest = new MathTest(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTID)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTLEVEL))),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTTYPE)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TOTALQUESTIONS))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TOTALCORRECTQUESTIONS))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TOTALINCORRECTQUESTONS))),
                        Double.parseDouble(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTSCORE))),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTDATE)));

                mathTests.add(mathTest);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return mathTests;
    }
    public ArrayList<Question> getQuestions(String [] testID) {
        mDatabase = mDbHelper.getReadableDatabase();

        Cursor cursor = mDatabase.query("tblQuestions", new String[]{"tblQuestions.column_testID, tblQuestions.column_num1, " +
                "tblQuestions.column_num2, tblQuestions.column_oper, tblQuestions.column_answer, " +
                "tblQuestions.column_resultEntered, tblQuestions.column_level, tblQuestions.column_correctStatus, tblQuestions.column_answeredStatus" }
                ,null,null,null,null,null);
        //String query = "select * from "+ TABLE_QUESTIONS + " where column_testID =?";
        //String query ="SELECT * FROM tblQuestions ";//WHERE column_testID = ?";
        //Cursor cursor = mDatabase.rawQuery(query, null);
        ArrayList<Question> questions = new ArrayList<>();

        if (cursor != null) {
            cursor.moveToFirst();

            do {
                Question question = new Question(
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_TESTID)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_NUM1))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_NUM2))),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_OPER)),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_ANSWER))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_RESULTENTERED))),
                        Integer.parseInt(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_LEVEL))),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_CORRECTSTATUS)),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_ANSWEREDSTATUS)));

                    questions.add(question);
            } while (cursor.moveToNext());
        }

        cursor.close();

        return questions;
    }

}
