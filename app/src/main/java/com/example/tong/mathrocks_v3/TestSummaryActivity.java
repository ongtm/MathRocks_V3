package com.example.tong.mathrocks_v3;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
            MyRecyclerViewAdapter adapter = new MyRecyclerViewAdapter(this,mMathTests);
            mRecyclerView = findViewById(R.id.test_recycler_view);
            mLayoutManager = new LinearLayoutManager(this);
            mRecyclerView.setLayoutManager(mLayoutManager);
            mRecyclerView.setAdapter(adapter);
        }
    }

    /*public void onClickDeleteItem(View view){
        TextView testing;
        testing= findViewById(R.id.rv_testDate);
        Toast.makeText(this,"I work!" + testing.getText(),Toast.LENGTH_SHORT).show();

    }*/
}
