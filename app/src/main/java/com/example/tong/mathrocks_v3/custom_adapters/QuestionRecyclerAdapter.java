package com.example.tong.mathrocks_v3.custom_adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;


import com.example.tong.mathrocks_v3.R;
import com.example.tong.mathrocks_v3.model.Question;

import java.util.List;

public class QuestionRecyclerAdapter extends RecyclerView.Adapter<QuestionRecyclerAdapter.QuestionRecyclerViewHolder> {

    private List<Question> mQuestion;
    private Context mContext;

    public QuestionRecyclerAdapter(Context context, List<Question> question){
        this.mContext = context;
        this.mQuestion = question;
    }


    public static class QuestionRecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView mQuestionLabel;
        public TextView mQuestionString;
        public TextView mQuestionStatus;
        public TextView mQuestionEntered;


        public QuestionRecyclerViewHolder(View questionView){
            super(questionView);

            mQuestionLabel = questionView.findViewById(R.id.question_label);
            mQuestionString = questionView.findViewById(R.id.question_string);
            mQuestionStatus = questionView.findViewById(R.id.question_status);
            mQuestionEntered = questionView.findViewById(R.id.question_entered);
        }
    }


    //Creates new view, places it in a view holder and inflates it and returns the view holder
    @Override
    public QuestionRecyclerAdapter.QuestionRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){

        LayoutInflater inflater = LayoutInflater.from(mContext);

        View questionView = inflater.inflate(R.layout.activity_question_summary, parent, false);

        QuestionRecyclerViewHolder questionRecyclerViewHolder = new QuestionRecyclerViewHolder(questionView);

        return questionRecyclerViewHolder;
    }

    @Override
    public void onBindViewHolder(QuestionRecyclerViewHolder holder, final int position){

        //MathTest myMathTest = mMathTest.get(position);
        Question myQuestion = mQuestion.get(position);

        //holder.mtestId.setText(myMathTest.getTestID());
        //holder.mtestLevel.setText(String.valueOf(myMathTest.getTestLevel()));
        holder.mQuestionLabel.setText(myQuestion.getTestID());

        String concatString = String.valueOf(myQuestion.getNum1()) + " " + myQuestion.getOper() + " " + String.valueOf(myQuestion.getNum2()) + " = " + String.valueOf(myQuestion.getAnswer());
        holder.mQuestionString.setText(concatString);

        concatString = String.valueOf(myQuestion.getNum1()) + " " + myQuestion.getOper() + " " + String.valueOf(myQuestion.getNum2()) + " = " + String.valueOf(myQuestion.getResultEntered());
        holder.mQuestionEntered.setText(concatString);

        holder.mQuestionStatus.setText(myQuestion.getCorrectStatus());

        /*holder.mDeleteButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

            }
        });

        holder.mViewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(mContext, QuestionSummaryActivity.class);
                intent.putExtra("testID",mMathTest.get(position).getTestID());
                mContext.startActivity(intent);
            }
        });*/
    }

    @Override
    public int getItemCount(){
        return mQuestion.size();
    }

}
