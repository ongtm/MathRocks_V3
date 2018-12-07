package com.example.tong.mathrocks_v3;

import android.content.ContentValues;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tong.mathrocks_v3.database.DataSource;
import com.example.tong.mathrocks_v3.model.MathTest;
import com.example.tong.mathrocks_v3.model.Question;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.UUID;

import static com.example.tong.mathrocks_v3.database.MathRocksDatabaseTables.TABLE_QUESTIONS;

public class TestScreenActivity extends AppCompatActivity {

    List<Question> listOfQuestions;

    private TextView mTextViewNum1;
    private TextView mTextViewNum2;
    private TextView mTextViewOper;
    private TextView mTextViewAnswer;
    private TextView mTextViewEnteredAnswer;
    private TextView mTextViewNumCorrectQuestions;
    private TextView mButtonScoreTest;
    private TextView mButtonCheckAnswer;
    private TextView mTextViewQuestionNumber;
    private TextView tvnumTotalQuestions;

    private int mCurrentQuestion = 0;
    private int mCorrectQuestionCount = 0;
    private int mNumberQuestionsAnswered = 0;

    private boolean hintsVisible;
    private int testLevel;
    private String testType;

    @Override
    protected void onCreate(Bundle savedInstanceState){

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_screen);

        //Get passed parameter from Main Activity
        String getVal = getIntent().getStringExtra("OPER_LEVEL");

        //Get Test Type (add, sub, mul, div)
        testType = getVal.substring(0,3);

        //Get Test Level (1,2,3,4,5,6)
        testLevel = Integer.parseInt(getVal.substring(3,4));

        //Get Random generated questions
        getTestQuestions(testType, testLevel);

        //Displays number of questions on screen
        tvnumTotalQuestions = findViewById(R.id.tvtotalpossible);
        tvnumTotalQuestions.setText(Integer.toString(listOfQuestions.size()));
        mTextViewQuestionNumber = findViewById(R.id.tvquestionNum);


        //Displays first question on screen
        UpdateQuestionTextView();


        //Hides views
        hideCorrectQuestionText();
        hideOtherTextViews();

        //Creates list of hints with correct answer seeded in list randomly.
        ListView listViewHints;
        listViewHints = findViewById(R.id.hintlist);
        String [] hintValues = generateHintList(testLevel,testType);

        //Fills list adapter with values and hides it
        final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(hintValues));
        listViewHints.setAdapter(adapter);
        listViewHints.setVisibility(View.GONE);
        hintsVisible = false;

    }
    private void getTestQuestions(String testType, int level){
        //method creates new test and gets random questions based on testType and testLevel
        String testID = toString().valueOf(System.currentTimeMillis());
        GetTestQuestions getTestQuestions = new GetTestQuestions();

        switch (level){
            case 1: listOfQuestions = getTestQuestions.generateLevel1Questions(testType,testID); break;
            case 2: listOfQuestions = getTestQuestions.generateLevel2Questions(testType,testID); break;
            case 3: listOfQuestions = getTestQuestions.generateLevel3Questions(testType,testID); break;
            case 4: listOfQuestions = getTestQuestions.generateLevel4Questions(testType,testID); break;
            case 5: listOfQuestions = getTestQuestions.generateLevel5Questions(testType,testID); break;
//            case 6: listOfQuestions = getTestQuestions.generateLevel6Questions(testType,testID); break;
            default: Toast.makeText(this,"ERROR",Toast.LENGTH_LONG).show(); break;
        }
    }

    public void UpdateQuestionTextView(){
        //method updates question text view based on new question
        //If question is already answered, displays result entered and if user got the question right or wrong.

        mTextViewNum1 = findViewById(R.id.tvnum1);
        mTextViewNum1.setText(Integer.toString(listOfQuestions.get(mCurrentQuestion).getNum1()));

        mTextViewNum2 = findViewById(R.id.tvnum2);
        mTextViewNum2.setText(Integer.toString(listOfQuestions.get(mCurrentQuestion).getNum2()));

        mTextViewOper = findViewById(R.id.tvoper);
        mTextViewOper.setText(listOfQuestions.get(mCurrentQuestion).getOper());

        mTextViewAnswer = findViewById(R.id.tvcorrectanswer);
        mTextViewAnswer.setText(Integer.toString(listOfQuestions.get(mCurrentQuestion).getAnswer()));

        mTextViewEnteredAnswer = findViewById(R.id.etresult);

        if(listOfQuestions.get(mCurrentQuestion).getAnsweredStatus()== "Yes"){

            mTextViewEnteredAnswer.setText(Integer.toString(listOfQuestions.get(mCurrentQuestion).getResultEntered()));
            mTextViewEnteredAnswer.setFocusable(false);

            if(listOfQuestions.get(mCurrentQuestion).getCorrectStatus()=="Correct"){
                Toast.makeText(this,"You got this question right.", Toast.LENGTH_SHORT).show();
            }else {
                Toast.makeText(this, "You got this question wrong.", Toast.LENGTH_SHORT).show();
            }
        }else{
            mTextViewEnteredAnswer.setText("");
            mTextViewEnteredAnswer.setFocusableInTouchMode(true);
        }

        mTextViewNumCorrectQuestions = findViewById(R.id.tvnumcorrect);
        mTextViewNumCorrectQuestions.setText(Integer.toString(mCorrectQuestionCount));
        mTextViewQuestionNumber.setText("Question # " + mCurrentQuestion);
        //Hides views
        hideCorrectQuestionText();
    }

    public void hideCorrectQuestionText(){
        //Method hides the correct answer text box --Can I remove this and just pull from the Question object?
        TextView textViewCAT;
        textViewCAT = findViewById(R.id.tvcorrectanswertext);
        textViewCAT.setText(getString(R.string.tvcorrectanswertext));
        textViewCAT.setVisibility(View.GONE);
    }

    public void hideOtherTextViews(){
        //Method hides other views as needed
        TextView tv1;
        TextView tv2;

        tv1=findViewById(R.id.testtype);
        tv1.setVisibility(View.GONE);

        tv2 = findViewById(R.id.tvcorrectanswer);
        tv2.setVisibility(View.GONE);

        mButtonScoreTest = findViewById(R.id.bnt_score_test);
        mButtonScoreTest.setVisibility(View.GONE);
    }

    public void onClickNextQuestion(View view){

        //Method increments question counter, generates new listing of hints and then updates view with new question
        mCurrentQuestion ++;
        if(listOfQuestions.size() > mCurrentQuestion ) {
            ListView listViewHints;
            listViewHints = findViewById(R.id.hintlist);
            String [] hintValues = generateHintList(testLevel,testType);

            final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(hintValues));
            listViewHints.setAdapter(adapter);
            listViewHints.setVisibility(View.GONE);
            hintsVisible = false;

            UpdateQuestionTextView();
        }
        else{
            Toast.makeText(this,"There are no more questions.",Toast.LENGTH_SHORT).show();
                mCurrentQuestion --;
                mButtonScoreTest = findViewById(R.id.bnt_score_test);
                mButtonScoreTest.setVisibility(View.VISIBLE);
                mButtonCheckAnswer = findViewById(R.id.bnt_checkanswer);
                mButtonCheckAnswer.setVisibility(View.GONE);

            }
        }

    public void onClickPreviousQuestion(View view){
        //Method decrements question counter, generates new listing of hints and then updates view with new question
        mCurrentQuestion --;

        if(mCurrentQuestion >= 0 ) {
            ListView listViewHints;
            listViewHints = findViewById(R.id.hintlist);

            String [] hintValues = generateHintList(testLevel,testType);

            final StableArrayAdapter adapter = new StableArrayAdapter(this, android.R.layout.simple_list_item_1, Arrays.asList(hintValues));

            listViewHints.setAdapter(adapter);

            listViewHints.setVisibility(View.GONE);
            hintsVisible = false;

            UpdateQuestionTextView();
        }
        else{
            mCurrentQuestion ++;
            Toast.makeText(this,"There are no more questions.",Toast.LENGTH_SHORT).show();
            if (mNumberQuestionsAnswered == 10){
                mButtonScoreTest = findViewById(R.id.bnt_score_test);
                mButtonScoreTest.setVisibility(View.VISIBLE);
                mButtonCheckAnswer = findViewById(R.id.bnt_checkanswer);
                mButtonCheckAnswer.setVisibility(View.GONE);
            }
        }
    }

    public void onClickCheckAnswer(View view) {
        //Method checks to see if answer was entered, then validates if answer is correct and updates screen and question object
        TextView textViewCA;
        TextView textViewResult;
        TextView textViewCAT;

        textViewCA = findViewById(R.id.tvcorrectanswer);
        textViewResult = findViewById(R.id.etresult);
        textViewCAT = findViewById(R.id.tvcorrectanswertext);

        if (textViewResult.getText().toString().equals("")) {
            Toast.makeText(this, "Please enter an answer", Toast.LENGTH_SHORT).show();
        } else {
            if (listOfQuestions.get(mCurrentQuestion).getAnsweredStatus() == "No") {
                mNumberQuestionsAnswered++;

                if (textViewCA.getText().toString().equals(textViewResult.getText().toString())) {
                    textViewCAT.setText("");
                    textViewCAT.setText("You entered the correct answer of " + textViewResult.getText().toString());
                    textViewCAT.setVisibility(View.VISIBLE);

                    listOfQuestions.get(mCurrentQuestion).setCorrectStatus("Correct");
                    listOfQuestions.get(mCurrentQuestion).setAnsweredStatus("Yes");
                    listOfQuestions.get(mCurrentQuestion).setResultEntered(Integer.parseInt(textViewResult.getText().toString()));

                    TextView numCorrect = findViewById(R.id.tvnumcorrect);
                    mCorrectQuestionCount++;
                    numCorrect.setText(Integer.toString(mCorrectQuestionCount));
                } else {
                    textViewCAT.setText("");
                    textViewCAT.setText("You entered an incorrect answer.  The correct answer is " + textViewCA.getText().toString());
                    textViewCAT.setVisibility(View.VISIBLE);
                    listOfQuestions.get(mCurrentQuestion).setCorrectStatus("Incorrect");
                    listOfQuestions.get(mCurrentQuestion).setAnsweredStatus("Yes");
                    listOfQuestions.get(mCurrentQuestion).setResultEntered(Integer.parseInt(textViewResult.getText().toString()));
                    ;
                }

            } else if (listOfQuestions.get(mCurrentQuestion).getAnsweredStatus() == "Yes") {
                Toast.makeText(this, "This question has already been answered", Toast.LENGTH_SHORT).show();
            }

            if (mNumberQuestionsAnswered == 10){
                mButtonScoreTest = findViewById(R.id.bnt_score_test);
                mButtonScoreTest.setVisibility(View.VISIBLE);
                mButtonCheckAnswer = findViewById(R.id.bnt_checkanswer);
                mButtonCheckAnswer.setVisibility(View.GONE);
            }
        }
    }
    public void onClickScoreTest(View view){
        //Method Scores a completed test and loads it and its questions into the SQLite database
        DataSource mDataSource = new DataSource(this);
        mDataSource.open();

        for(int i=0; i < listOfQuestions.size(); i++){
            ContentValues contentValues;
            contentValues = listOfQuestions.get(i).questionToValues();
            mDataSource.onInsert(contentValues,"tblQuestions");

        }

        String testID = listOfQuestions.get(mCurrentQuestion).getTestID();
        int level = testLevel;
        int testSize = listOfQuestions.size();
        int mIncorrectQuestions = testSize - mCorrectQuestionCount;
        //double dCorrectQuestions = Double.valueOf(mCorrectQuestionCount);
        double mScore = Math.round(((Double.valueOf(mCorrectQuestionCount)/Double.valueOf(testSize))*100));
        Date thisDate = new Date();
        String timeStamp = thisDate.toString();





        MathTest thisTest = new MathTest(
                testID,
                level,
                testType,
                testSize,
                mCorrectQuestionCount,
                mIncorrectQuestions,
                mScore,
                timeStamp);

        ContentValues contentValues;
        contentValues = thisTest.mathTestToValues();
        mDataSource.onInsert(contentValues,"tblMathTests");

        Toast.makeText(this,"Test scored and saved.", Toast.LENGTH_LONG).show();
        finish();
    }
    public void onClickHint(View view){
        //Method displays a list of random hints
        ListView listViewHints;
        listViewHints = findViewById(R.id.hintlist);

        if(hintsVisible == false){
            listViewHints.setVisibility(View.VISIBLE);
            hintsVisible = true;
        }
        else{
            listViewHints.setVisibility(View.GONE);
            hintsVisible = false;
        }
    }

    public String[] generateHintList(int level,String testType) {
        //Method generates a list of random hints and seeds the correct answer randomly into the list.

        String[] hintValues = new String[]{"0", "0", "0", "0"};
        ArrayList<Integer> arrayHint = new ArrayList<Integer>();

        Random thisRandom = new Random();
        int posCorrect = thisRandom.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == posCorrect) {
                hintValues[i] = Integer.toString(listOfQuestions.get(mCurrentQuestion).getAnswer());
                arrayHint.add(listOfQuestions.get(mCurrentQuestion).getAnswer());
            } else {
                hintValues[i] = Integer.toString(getRandomHint(level, testType));
                arrayHint.add(getRandomHint(level,testType));
            }
        }
        return hintValues;
    }
    public int getRandomHint(int testLevel, String testType){
        //Method generates a random number for a hint based on test type and test level
        Random thisRandom = new Random();
        int randHint = 0;
        switch (testType){
            case "add":
                switch (testLevel){
                    case 1: randHint = thisRandom.nextInt(25); break;
                    case 2: randHint = thisRandom.nextInt(41); break;
                    case 3: randHint = thisRandom.nextInt(201); break;
                    case 4: randHint = thisRandom.nextInt(1101); break;
                    case 5: randHint = thisRandom.nextInt(2001); break;
                }

            case "sub":
                switch (testLevel){
                    case 1: randHint = thisRandom.nextInt(25); break;
                    case 2: randHint = thisRandom.nextInt(41); break;
                    case 3: randHint = thisRandom.nextInt(201); break;
                    case 4: randHint = thisRandom.nextInt(1101); break;
                    case 5: randHint = thisRandom.nextInt(2001); break;
                }

            case "mul":
                switch (testLevel){
                    case 1: randHint = thisRandom.nextInt(145); break;
                    case 2: randHint = thisRandom.nextInt(401); break;
                    case 3: randHint = thisRandom.nextInt(10001); break;
                    case 4: randHint = thisRandom.nextInt(100001); break;
                    case 5: randHint = thisRandom.nextInt(1000001); break;
                }
            case "div":
                switch (testLevel){
                    case 1: randHint = thisRandom.nextInt(13); break;
                    case 2: randHint = thisRandom.nextInt(21); break;
                    case 3: randHint = thisRandom.nextInt(101); break;
                    case 4: randHint = thisRandom.nextInt(1001); break;
                    case 5: randHint = thisRandom.nextInt(1001); break;
                }
        }

        return randHint;
    }

    private class StableArrayAdapter extends ArrayAdapter<String> {
        //Class is used to display list of hints
        HashMap<String, Integer> mIDMap = new HashMap<String, Integer>();

        public StableArrayAdapter(Context context, int textViewResourceId, List<String>objects){
            super(context,textViewResourceId, objects);
            for(int i = 0; i< objects.size();i++){
                mIDMap.put(objects.get(i),i);
            }
        }

        @Override
        public long getItemId(int position){
            String item = getItem(position);
            return mIDMap.get(item);
        }

        @Override
        public boolean hasStableIds(){
            return true;
        }
    }

}
