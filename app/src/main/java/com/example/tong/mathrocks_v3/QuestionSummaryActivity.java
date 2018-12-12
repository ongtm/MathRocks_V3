package com.example.tong.mathrocks_v3;


import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import com.example.tong.mathrocks_v3.custom_adapters.QuestionRecyclerAdapter;
import com.example.tong.mathrocks_v3.database.DataSource;
import com.example.tong.mathrocks_v3.model.Question;
import java.util.ArrayList;
import java.util.List;

public class QuestionSummaryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DataSource mDataSource;
    List<Question> mQuestions = new ArrayList<>();

    private SQLiteDatabase mDatabase;
    SQLiteOpenHelper mDbHelper;


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_summary_recylcerview);

        String testID = getIntent().getStringExtra("testID");

        mDataSource = new DataSource(this);
        mDataSource.open();

//        mDatabase = mDbHelper.getReadableDatabase();

        if(mDataSource.isEmpty("tblQuestions")== false){

            mQuestions = mDataSource.getQuestions(testID);

            //Get items for recyclerview
            QuestionRecyclerAdapter adapter = new QuestionRecyclerAdapter(this,mQuestions);
            mRecyclerView = findViewById(R.id.question_recycler_view);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }
        //String tb = testID[0];

    }
}
