package com.example.tong.mathrocks_v3.database;

import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;



import com.example.tong.mathrocks_v3.R;
import com.example.tong.mathrocks_v3.model.FAQ;
import com.example.tong.mathrocks_v3.model.MathTest;
import com.example.tong.mathrocks_v3.model.Question;

import java.util.ArrayList;
import java.util.List;

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

      if (cursor != null & cursor.getCount()>0) {
            cursor.moveToFirst();

            do {
                MathTest mathTest = new MathTest(
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTID)),
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
    public ArrayList<Question> getQuestions(String testID) {
        mDatabase = mDbHelper.getReadableDatabase();

        String [] cols= {"tblQuestions.column_testID, tblQuestions.column_num1, tblQuestions.column_num2, tblQuestions.column_oper, tblQuestions.column_answer,tblQuestions.column_resultEntered, tblQuestions.column_level, tblQuestions.column_correctStatus, tblQuestions.column_answeredStatus"};        //String [] columns = {MathRocksDatabaseTables.COLUMN_MATHTEST_TESTID, MathRocksDatabaseTables.COLUMN_QUESTION_NUM1,MathRocksDatabaseTables.COLUMN_QUESTION_NUM2, MathRocksDatabaseTables.COLUMN_QUESTION_OPER,MathRocksDatabaseTables.COLUMN_QUESTION_ANSWER, MathRocksDatabaseTables}

        Cursor cursor = mDatabase.query(MathRocksDatabaseTables.TABLE_QUESTIONS,
                cols, MathRocksDatabaseTables.COLUMN_MATHTEST_TESTID + "=?", new String[] {testID},null,null,null, null);
        //String thisString = toString().valueOf(testID);
        //Cursor cursor = mDatabase.rawQuery("SELECT * FROM tblQuestions",null);

        ArrayList<Question> questions = new ArrayList<>();

        if (cursor != null & cursor.getCount() >0) {
            cursor.moveToFirst();

            do {
                /*String c = cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_QUESTION_TESTID));
                if(c.equals(testID)){*/
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
                //}
            } while (cursor.moveToNext());
        }

        cursor.close();

        return questions;
    }

    public void loadFAQTable(){

        mDatabase = mDbHelper.getWritableDatabase();


      FAQ faq1 = new FAQ(null,"How many questions are there in a test?","There are ten (10) questions in each test.");
      FAQ faq2 = new FAQ(null,"How do I get a hint for a question?","Tap on the Get Hint button in the bottom left corner to view the hints for each question.");
      FAQ faq3 = new FAQ(null,"What do the levels for the tests represent?","The levels represent the difficulty of each test.  " +
              "Level 1  questions are for the basic 0-12 addition/subtraction/multiplication/division.  " +
                      "Level 2 covers 0 - 20. " +
                      "Level 3 covers 0 - 100. " +
                      "Level 4 allows for the numbers being computed to be different lengths. " +
                      "The first number covers the range 0-1000 and the second number covers the range 0 - 100." +
                      "Level 5 covers 0 - 1000 ");
      FAQ faq4 = new FAQ(null,"How can I see the tests I have taken?","You can view the tests that you have taken by tapping on the menu bar and selecting Score Summary");
      FAQ faq5 = new FAQ(null,"How do I view the questions for the tests I have taken?","To view the questions for a taken test, tap on the Chart icon next to the test on the Test Summary screen.");
      FAQ faq6 = new FAQ(null,"Can I retake a test?","You can not retake tests at this time.");
      FAQ faq7 = new FAQ(null,"How do I delete a test?","A test can be deleted by taping on the Trash can icon next to the test on the Test Summary screen.");

      List<FAQ> allFAQs = new ArrayList<>();
      allFAQs.add(faq1);
      allFAQs.add(faq2);
      allFAQs.add(faq3);
      allFAQs.add(faq4);
      allFAQs.add(faq5);
      allFAQs.add(faq6);
      allFAQs.add(faq7);


      for(int i = 0 ; i < allFAQs.size(); i++){
        ContentValues contentValues;
        contentValues= allFAQs.get(i).faqToValues();
        onInsert(contentValues,"tblFAQ");
      }


    }

    public List<FAQ> getFAQs(){
        mDatabase = mDbHelper.getWritableDatabase();

        String[] cols = {"tblFAQ.column_faqID, tblFAQ.column_faqQuestion, tblFAQ.column_faqAnswer"};
        Cursor cursor = mDatabase.query("tblFAQ",cols,null,null,null,null,null,null);

        ArrayList<FAQ> allFAQ = new ArrayList<>();

        if(cursor != null && cursor.getCount()>0){
            cursor.moveToFirst();

            do{
                FAQ faq = new FAQ(cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_FAQ_FAQID)),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_FAQ_FAQQUESTION)),
                        cursor.getString(cursor.getColumnIndex(MathRocksDatabaseTables.COLUMN_FAQ_FAQANSWER)));

                allFAQ.add(faq);
            }while (cursor.moveToNext());

        }
        return allFAQ;
    }

    public void deleteTest(String testID){
        mDatabase = mDbHelper.getWritableDatabase();
        mDatabase.delete("tblMathTests","column_testID=?", new String[] {testID});

    }
}
