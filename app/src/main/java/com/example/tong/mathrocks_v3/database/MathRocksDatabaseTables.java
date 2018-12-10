package com.example.tong.mathrocks_v3.database;

public class MathRocksDatabaseTables {

    public static final String TABLE_QUESTIONS = "tblQuestions";
    public static final String COLUMN_QUESTION_NUM1 = "column_num1";
    public static final String COLUMN_QUESTION_NUM2 = "column_num2";
    public static final String COLUMN_QUESTION_OPER =  "column_oper";
    public static final String COLUMN_QUESTION_ANSWER = "column_answer";
    public static final String COLUMN_QUESTION_RESULTENTERED = "column_resultEntered";
    public static final String COLUMN_QUESTION_LEVEL = "column_level";
    public static final String COLUMN_QUESTION_CORRECTSTATUS = "column_correctStatus";
    public static final String COLUMN_QUESTION_ANSWEREDSTATUS= "column_answeredStatus";
    public static final String COLUMN_QUESTION_TESTID = "column_testID";


    public static final String SQL_CREATE_QUESTIONS_TABLE =
            "CREATE TABLE " + TABLE_QUESTIONS + "( " +
                    COLUMN_QUESTION_NUM1 + " INTEGER, " +
                    COLUMN_QUESTION_NUM2 + " INTEGER, " +
                    COLUMN_QUESTION_OPER + " TEXT, " +
                    COLUMN_QUESTION_ANSWER + " INTEGER, " +
                    COLUMN_QUESTION_RESULTENTERED + " INTEGER, " +
                    COLUMN_QUESTION_LEVEL + " INTEGER, " +
                    COLUMN_QUESTION_CORRECTSTATUS + " TEXT, " +
                    COLUMN_QUESTION_ANSWEREDSTATUS + " TEXT, " +
                    COLUMN_QUESTION_TESTID + " TEXT " + ");";

    public static final String SQL_DELETE_QUESTIONS_TABLE = "DROP TABLE " + TABLE_QUESTIONS;

    public static final String TABLE_MATHTESTS = "tblMathTests";
    public static final String COLUMN_MATHTEST_TESTID = "column_testID";
    public static final String COLUMN_MATHTEST_TESTLEVEL = "column_testLevel";
    public static final String COLUMN_MATHTEST_TESTTYPE = "column_testType";
    public static final String COLUMN_MATHTEST_TOTALQUESTIONS = "column_totalQuestions";
    public static final String COLUMN_MATHTEST_TOTALCORRECTQUESTIONS = "column_totalCorrectQuestions";
    public static final String COLUMN_MATHTEST_TOTALINCORRECTQUESTONS = "column_totalIncorrectQuestions";
    public static final String COLUMN_MATHTEST_TESTSCORE = "column_testScore";
    public static final String COLUMN_MATHTEST_TESTDATE = "column_testDate";


    public static final String SQL_CREATE_MATHTESTS_TABLE =
            "CREATE TABLE " + TABLE_MATHTESTS + "( " +
                    COLUMN_MATHTEST_TESTID + " TEXT, " +
                    COLUMN_MATHTEST_TESTLEVEL + " INTEGER, " +
                    COLUMN_MATHTEST_TESTTYPE + " TEXT, " +
                    COLUMN_MATHTEST_TOTALQUESTIONS + " INTEGER, " +
                    COLUMN_MATHTEST_TOTALCORRECTQUESTIONS + " INTEGER, " +
                    COLUMN_MATHTEST_TOTALINCORRECTQUESTONS + " INTEGER, " +
                    COLUMN_MATHTEST_TESTSCORE + " DOUBLE, " +
                    COLUMN_MATHTEST_TESTDATE + " TEXT" + ");";

    public static final String SQL_DELETE_MATHTESTS_TABLE = "DROP TABLE " + TABLE_MATHTESTS;

    public static final String TABLE_FAQ = "tblFAQ";
    public static final String COLUMN_FAQ_FAQID = "column_faqID";
    public static final String COLUMN_FAQ_FAQQUESTION = "column_faqQuestion";
    public static final String COLUMN_FAQ_FAQANSWER = "column_faqAnswer";

    public static final String SQL_CREATE_FAQ_TABLE =
            "CREATE TABLE " + TABLE_FAQ + "( " +
                    COLUMN_FAQ_FAQID + " TEXT, " +
                    COLUMN_FAQ_FAQQUESTION + " TEXT, " +
                    COLUMN_FAQ_FAQANSWER + " TEXT" + ");";

    public static final String SQL_DELETE_FAQ_TABLE = "DROP TABLE " + TABLE_FAQ;

}
