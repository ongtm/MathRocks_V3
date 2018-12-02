package com.example.tong.mathrocks_v3.custom_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tong.mathrocks_v3.R;
import com.example.tong.mathrocks_v3.model.MathTest;

import java.util.List;

public class MyRecyclerViewAdapter extends RecyclerView.Adapter<MyRecyclerViewAdapter.MyRecyclerViewHolder>{

    private List<MathTest> mMathTest;
    private Context mContext;

    public MyRecyclerViewAdapter(Context context, List<MathTest> mathTest){
        this.mContext = context;
        this.mMathTest = mathTest;
    }


    public static class MyRecyclerViewHolder extends RecyclerView.ViewHolder{
        //public View mView;
        public TextView testId;
        public TextView testLevel;
        public TextView testType;
        public TextView numQuestions;
        public TextView numCorrect;
        public TextView numIncorrect;
        public TextView testScore;
        public TextView testDate;



        public MyRecyclerViewHolder(View testView){
            super(testView);

            testId = testView.findViewById(R.id.testId);
            testLevel = testView.findViewById(R.id.testLevel);
            testType = testView.findViewById(R.id.testType);
            numQuestions = testView.findViewById(R.id.numQuestions);
            numCorrect = testView.findViewById(R.id.numCorrect);
            numIncorrect = testView.findViewById(R.id.numIncorrect);
            testScore= testView.findViewById(R.id.testScore);
            testDate = testView.findViewById(R.id.testDate);
        }
    }


    //Creates new view, places it in a view holder and inflates it and returns the view holder
    @Override
    public MyRecyclerViewAdapter.MyRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View testView = inflater.inflate(R.layout.activity_test_summary, parent, false);

        MyRecyclerViewHolder myRecyclerViewHolder = new MyRecyclerViewHolder(testView);

        return myRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(MyRecyclerViewHolder holder, int position){

        MathTest myMathTest = mMathTest.get(position);

        holder.testId.setText(myMathTest.getTestID());
        holder.testLevel.setText(myMathTest.getTestLevel());
        holder.testType.setText(myMathTest.getTestType());
        holder.numQuestions.setText(myMathTest.getTotalNumQuestions());
        holder.numCorrect.setText(String.valueOf(myMathTest.getTotalCorrectQuestions()));
        holder.numIncorrect.setText(String.valueOf(myMathTest.getTotalIncorrectQuestions()));
        holder.testScore.setText(String.valueOf(myMathTest.getTestScore()));
        holder.testDate.setText(String.valueOf(myMathTest.getTestDate()));

    }

    @Override
    public int getItemCount(){
        return mMathTest.size();
    }

}
