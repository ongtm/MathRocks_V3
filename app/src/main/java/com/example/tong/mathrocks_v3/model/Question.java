package com.example.tong.mathrocks_v3.model;

import android.content.ContentValues;

import com.example.tong.mathrocks_v3.database.MathRocksDatabaseTables;

import java.util.UUID;

public class Question {
    private int num1;
    private int num2;
    private String oper;
    private int answer;
    private int resultEntered;
    private int level;
    private String correctStatus;
    private String answeredStatus;
    private String testID;

    //Public no argument constructor
    public Question(){

    }

    //Public standard constructor
    public Question(String testID, int num1, int num2, String oper, int answer, int resultEntered, int level, String correctStatus, String answeredStatus){
        this.testID = testID;
        this.num1 = num1;
        this.num2 = num2;
        this.oper = oper;
        this.answer = answer;
        this.resultEntered = resultEntered;
        this.level = level;
        this.correctStatus = correctStatus;
        this.answeredStatus = answeredStatus;
    }

    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID){
        this.testID = testID;
    }

    public int getNum1(){
        return this.num1;
    }

    public void setNum1(int num1){
        this.num1 = num1;
    }

    public int getNum2(){
        return this.num2;
    }

    public void setNum2(int num2){
        this.num2 = num2;
    }

    public String getOper(){
        return this.oper;
    }

    public void setOper(String oper){
        this.oper = oper;
    }

    public int getAnswer(){
        return this.answer;
    }

    public void setAnswer(int answer){
        this.answer = answer;
    }

    public int getResultEntered(){
        return this.resultEntered;
    }

    public void setResultEntered(int resultEntered){
        this.resultEntered = resultEntered;
    }

    public int getLevel(){ return this.level; }

    public void setLevel(int level){
        this.level = level;
    }

    public String getCorrectStatus(){
        return this.correctStatus;
    }

    public void setCorrectStatus(String correctStatus){
        this.correctStatus = correctStatus;
    }

    public String getAnsweredStatus() {return answeredStatus;}

    public void setAnsweredStatus(String answeredStatus) {this.answeredStatus = answeredStatus;}

    public ContentValues questionToValues(){
        ContentValues contentValues = new ContentValues(9);

        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_NUM1,num1);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_NUM2,num2);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_OPER,oper);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_ANSWER,answer);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_RESULTENTERED,resultEntered);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_LEVEL,level);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_CORRECTSTATUS,correctStatus);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_ANSWEREDSTATUS,answeredStatus);
        contentValues.put(MathRocksDatabaseTables.COLUMN_QUESTION_TESTID,testID);

        return contentValues;
    }

}
