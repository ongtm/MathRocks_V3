package com.example.tong.mathrocks_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.example.tong.mathrocks_v3.custom_adapters.MyRecyclerViewAdapter;
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

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_summary_recylcerview);

        mDataSource = new DataSource(this);
        mDataSource.open();

        if(mDataSource.isEmpty("tblQuestions")== false){
            mQuestions = mDataSource.getQuestions();

            //Get items for recyclerview
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this,mQuestions);
            mRecyclerView = findViewById(R.id.test_recycler_view);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }

        Toast.makeText(this,"this test id is " + getIntent().getStringExtra("testID"),Toast.LENGTH_SHORT).show();
    }
}
