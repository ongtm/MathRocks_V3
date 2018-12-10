package com.example.tong.mathrocks_v3.model;

import android.content.ContentValues;
import android.content.Context;

import com.example.tong.mathrocks_v3.database.MathRocksDatabaseTables;


public class FAQ {
    private String faqID;
    private String faqQuestion;
    private String faqAnswer;

    public FAQ(String faqID, String faqQuestion, String faqAnswer){
        if(faqID == null){
            this.faqID = toString().valueOf(System.currentTimeMillis());
        }
        else {
            this.faqID = faqID;
        }
        this.faqQuestion = faqQuestion;
        this.faqAnswer = faqAnswer;
    }

    public String getFaqQuestion() {
        return faqQuestion;
    }

    public void setFaqQuestion(String faqQuestion) {
        this.faqQuestion = faqQuestion;
    }

    public String getFaqID() {
        return faqID;
    }

    public void setFaqID(String faqID) {
        this.faqID = faqID;
    }

    public String getFaqAnswer() {
        return faqAnswer;
    }

    public void setFaqAnswer(String faqAnswer) {
        this.faqAnswer = faqAnswer;
    }


    public ContentValues faqToValues(){
        ContentValues contentValues = new ContentValues(3);
        contentValues.put(MathRocksDatabaseTables.COLUMN_FAQ_FAQID,faqID);
        contentValues.put(MathRocksDatabaseTables.COLUMN_FAQ_FAQQUESTION,faqQuestion);;
        contentValues.put(MathRocksDatabaseTables.COLUMN_FAQ_FAQANSWER,faqAnswer);

        return contentValues;
    }
    }
