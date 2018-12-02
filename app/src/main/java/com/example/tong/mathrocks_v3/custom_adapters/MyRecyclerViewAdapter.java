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
        public TextView mtestId;
        public TextView mtestLevel;
        public TextView mtestType;
        public TextView mnumQuestions;
        public TextView mnumCorrect;
        public TextView mnumIncorrect;
        public TextView mtestScore;
        public TextView mtestDate;



        public MyRecyclerViewHolder(View testView){
            super(testView);

            mtestId = testView.findViewById(R.id.rv_testId);
            mtestLevel = testView.findViewById(R.id.rv_testLevel);
            mtestType = testView.findViewById(R.id.rv_testType);
            mnumQuestions = testView.findViewById(R.id.rv_numQuestions);
            mnumCorrect = testView.findViewById(R.id.rv_numCorrect);
            mnumIncorrect = testView.findViewById(R.id.rv_numIncorrect);
            mtestScore= testView.findViewById(R.id.rv_testScore);
            mtestDate = testView.findViewById(R.id.rv_testDate);
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

        holder.mtestId.setText(myMathTest.getTestID());
        holder.mtestLevel.setText(String.valueOf(myMathTest.getTestLevel()));
        holder.mtestType.setText(myMathTest.getTestType());
        holder.mnumQuestions.setText(String.valueOf(myMathTest.getTotalNumQuestions()));
        holder.mnumCorrect.setText(String.valueOf(myMathTest.getTotalCorrectQuestions()));
        holder.mnumIncorrect.setText(String.valueOf(myMathTest.getTotalIncorrectQuestions()));
        holder.mtestScore.setText(String.valueOf(myMathTest.getTestScore()));
        holder.mtestDate.setText(myMathTest.getTestDate());

    }

    @Override
    public int getItemCount(){
        return mMathTest.size();
    }

}
