package com.example.tong.mathrocks_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;


import com.example.tong.mathrocks_v3.custom_adapters.MyRecyclerViewAdapter;
import com.example.tong.mathrocks_v3.database.DataSource;
import com.example.tong.mathrocks_v3.model.MathTest;

import java.util.ArrayList;
import java.util.List;

public class TestSummaryActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private DataSource mDataSource;
    List<MathTest> mMathTests = new ArrayList<>();


    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_summary_recyclerview);

        //Open Database link
        mDataSource = new DataSource(this);
        mDataSource.open();

        //Transfer test info from database to array for recycler view
        if(mDataSource.isEmpty("tblMathTests")== false){
            mMathTests = mDataSource.getMathTests();

            //Get items for recyclerview
            mAdapter = new MyRecyclerViewAdapter(this,mMathTests);
            mRecyclerView = findViewById(R.id.test_recycler_view);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(mAdapter);
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mInflator = getMenuInflater();
        mInflator.inflate(R.menu.menu_toolbar_child_testsummary,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId();

        if(itemID == R.id.menu_scoreSummary_child){
            //navigate to score summary
            startActivity(new Intent(TestSummaryActivity.this,TestSummaryActivity.class));
        }
        else if (itemID == R.id.menu_about_child){
            //navigate to about
            startActivity(new Intent(TestSummaryActivity.this, FAQActivity.class));
        }
        else{
            //No action
        }
        return super.onOptionsItemSelected(item);

    }

}
