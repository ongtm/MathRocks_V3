package com.example.tong.mathrocks_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.tong.mathrocks_v3.custom_adapters.MyRecyclerViewAdapter;
import com.example.tong.mathrocks_v3.model.MathTest;

import java.util.List;

public class TestSummaryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    List<MathTest> mMathTestList;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_test_summary_recyclerview);

        MyRecyclerViewAdapter recyclerViewAdapter = new MyRecyclerViewAdapter(this,mMathTestList);

        //Recycler View
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        //Setting for keeping layout size the same regardless of content
        //mRecyclerView.setHasFixedSize(true);

        //using linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        //mAdapter = new MyAdapter(myDataset);

        mRecyclerView.setAdapter(recyclerViewAdapter);

    }
}
