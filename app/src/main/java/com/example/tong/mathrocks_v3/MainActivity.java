package com.example.tong.mathrocks_v3;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.tong.mathrocks_v3.database.DataSource;
import com.example.tong.mathrocks_v3.model.MathTest;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener, View.OnClickListener{

    public DataSource mDataSource;
    public int levelSelected;
    public String level;
    public String testName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Setting up database
        mDataSource = new DataSource(this);
        mDataSource.open();

        //Assign Spinners objects by ID
        Spinner spinnerAdd = findViewById(R.id.spinneradd);

        //Creating array to hold items for spinner
        ArrayList<Integer> mathLevels = new ArrayList<Integer>();
        mathLevels.add(1);
        mathLevels.add(2);
        mathLevels.add(3);
        mathLevels.add(4);
        mathLevels.add(5);
        //mathLevels.add(6);

        //Creating array adapter for spinner
        ArrayAdapter<Integer> spinnerAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item,mathLevels);

        //Set on Click listener for spinner
        spinnerAdd.setOnItemSelectedListener(this);

        //attaching adapter to the spinner
        spinnerAdd.setAdapter(spinnerAdapter);

        //setting onClickListeners for each imagebutton on activity
        ImageButton add1 = findViewById(R.id.imgadd1);
        add1.setOnClickListener(this);

        ImageButton sub1 = findViewById(R.id.imgsub1);
        sub1.setOnClickListener(this);

        ImageButton mul1 = findViewById(R.id.imgmul1);
        mul1.setOnClickListener(this);

        ImageButton div1 = findViewById(R.id.imgdiv1);
        div1.setOnClickListener(this);


    }

    public void onClick(View view) {

        Intent thisIntent = new Intent(getBaseContext(),TestScreenActivity.class);

        switch (levelSelected) {
            case 1: level="1"; break;
            case 2: level="2"; break;
            case 3: level="3"; break;
            case 4: level="4"; break;
            case 5: level="5"; break;
            //  case 6: level="6"; break;
            default: Toast.makeText(this, "Please select a test level.", Toast.LENGTH_SHORT).show(); break;
        }

        switch (view.getId()){
            case R.id.imgadd1: testName = "add" + level; break;
            case R.id.imgsub1: testName = "sub" + level; break;
            case R.id.imgmul1: testName = "mul" + level; break;
            case R.id.imgdiv1: testName = "div" + level; break;
            default: Toast.makeText(this,"ERROR-Exiting Application",Toast.LENGTH_LONG).show(); break;
        }
        thisIntent.putExtra("OPER_LEVEL", testName);
        startActivity(thisIntent);
    }

    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        Integer item = (Integer)parent.getItemAtPosition(position);

        //This may be the correct form
        //String item = parent.getItemAtPosition(position).toString();

        //Setting level
        levelSelected= item;
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        //Throw error?--Auto generated method.
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater mInflator = getMenuInflater();
        mInflator.inflate(R.menu.menu_toolbar,menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        int itemID = item.getItemId();

        if(itemID == R.id.menu_scoreSummary){
            //navigate to score summary
            startActivity(new Intent(MainActivity.this,TestSummaryActivity.class));
        }
        else if (itemID == R.id.menu_about){
            startActivity(new Intent(MainActivity.this, FAQActivity.class));
        }
        else{
            //No action
        }
        return super.onOptionsItemSelected(item);

    }

    @Override
    protected void onPause(){
        super.onPause();
        mDataSource.close();
    }

    @Override
    protected void onResume(){
        super.onResume();
        mDataSource.open();
    }

}
