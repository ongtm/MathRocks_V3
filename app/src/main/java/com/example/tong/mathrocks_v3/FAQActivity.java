package com.example.tong.mathrocks_v3;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.tong.mathrocks_v3.custom_adapters.FAQRecyclerAdapter;
import com.example.tong.mathrocks_v3.custom_adapters.MyRecyclerViewAdapter;
import com.example.tong.mathrocks_v3.database.DataSource;
import com.example.tong.mathrocks_v3.model.FAQ;


import java.util.ArrayList;
import java.util.List;

public class FAQActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    DataSource mDataSource;
    List<FAQ> mFAQ = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_faq_recyclerview);

        //Open Database link
        mDataSource = new DataSource(this);
        mDataSource.open();


        //Transfer test info from database to array for recycler view
        if (mDataSource.isEmpty("tblFAQ") == true) {
            mDataSource.loadFAQTable();
        }
        mFAQ = mDataSource.getFAQs();

        //Get items for recyclerview
        FAQRecyclerAdapter adapter = new FAQRecyclerAdapter(this, mFAQ);
        mRecyclerView = findViewById(R.id.faq_recycler_view);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mInflator = getMenuInflater();
        mInflator.inflate(R.menu.menu_toolbar_child_faq,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId();

        if(itemID == R.id.menu_scoreSummary_child){
            //navigate to score summary
            startActivity(new Intent(FAQActivity.this,TestSummaryActivity.class));
        }
        else if (itemID == R.id.menu_about_child){
            //navigate to about
            startActivity(new Intent( FAQActivity.this,FAQActivity.class));
        }
        else{
            //No action
        }
        return super.onOptionsItemSelected(item);

    }




}
