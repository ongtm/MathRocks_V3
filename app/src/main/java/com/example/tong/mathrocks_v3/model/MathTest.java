package com.example.tong.mathrocks_v3.model;

import android.content.ContentValues;

import com.example.tong.mathrocks_v3.database.MathRocksDatabaseTables;

import java.util.Date;
import java.util.UUID;

public class MathTest {
    private String testID;
    private int testLevel;
    private String testType;
    private int totalNumQuestions;
    private int totalCorrectQuestions;
    private int totalIncorrectQuestions;
    private double testScore;
    private String testDate;


    //Public no argument constructor
    public MathTest(){

    }

    public MathTest(String testID,  int testLevel, String testType, int totalNumQuestions, int totalCorrectQuestions, int totalIncorrectQuestions, double testScore, String testDate){

        if(testID == null){
            this.testID = UUID.randomUUID().toString();
        }
        this.testLevel = testLevel;
        this.testType = testType;
        this.totalNumQuestions = totalNumQuestions;
        this.totalCorrectQuestions= totalCorrectQuestions;
        this.totalIncorrectQuestions = totalIncorrectQuestions;
        this.testScore = testScore;
        this.testDate = testDate;

    }


    public String getTestID() {
        return testID;
    }

    public void setTestID(String testID) {
        this.testID = testID;
    }

    public int getTotalNumQuestions() {
        return totalNumQuestions;
    }

    public void setTotalNumQuestions(int totalNumQuestions) {
        this.totalNumQuestions = totalNumQuestions;
    }

    public int getTotalCorrectQuestions() {
        return totalCorrectQuestions;
    }

    public void setTotalCorrectQuestions(int totalCorrectQuestions) {
        this.totalCorrectQuestions = totalCorrectQuestions;
    }

    public int getTotalIncorrectQuestions() {
        return totalIncorrectQuestions;
    }

    public void setTotalIncorrectQuestions(int totalIncorrectQuestions) {
        this.totalIncorrectQuestions = totalIncorrectQuestions;
    }

    public double getTestScore() {
        return testScore;
    }

    public void setTestScore(double testScore) {
        this.testScore = testScore;
    }

    public String getTestDate() {
        return testDate;
    }

    public void setTestDate(String testDate) {
        this.testDate = testDate;
    }


    public int getTestLevel() {
        return testLevel;
    }

    public void setTestLevel(int testLevel) {
        this.testLevel = testLevel;
    }

    public String getTestType() { return testType; }

    public void setTestType(String testType) { this.testType = testType; }

    public ContentValues mathTestToValues(){
        ContentValues contentValues = new ContentValues(8);

        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTID,testID);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTLEVEL,testLevel);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTTYPE,testType);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TOTALQUESTIONS,totalNumQuestions);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TOTALCORRECTQUESTIONS,totalCorrectQuestions);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TOTALINCORRECTQUESTONS,totalIncorrectQuestions);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTSCORE,testScore);
        contentValues.put(MathRocksDatabaseTables.COLUMN_MATHTEST_TESTDATE,testDate);

        return  contentValues;
    }

}
