package com.example.tong.mathrocks_v3.custom_adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.tong.mathrocks_v3.R;
import com.example.tong.mathrocks_v3.model.FAQ;

import java.util.List;

public class FAQRecyclerAdapter extends RecyclerView.Adapter <FAQRecyclerAdapter.FAQRecyclerViewHolder> {

    private List<FAQ> mFaq;
    private Context mContext;

    public FAQRecyclerAdapter (Context context, List<FAQ> faq){
        this.mContext = context;
        this.mFaq = faq;
    }

    public static class FAQRecyclerViewHolder extends RecyclerView.ViewHolder{
        public TextView mFaqID;
        public TextView mFaqQuestionLabel;
        public TextView mFaqQuestion;
        public TextView mFaqAnswerLabel;
        public TextView mFaqAnswer;

        public FAQRecyclerViewHolder(View faqView){
            super(faqView);
            mFaqID = faqView.findViewById(R.id.faq_id);
            mFaqQuestionLabel = faqView.findViewById(R.id.faq_question_label);
            mFaqQuestion = faqView.findViewById(R.id.faq_question);
            mFaqAnswerLabel = faqView.findViewById(R.id.faq_answer_label);
            mFaqAnswer = faqView.findViewById(R.id.faq_answer);
        }
    }

    @Override
    public FAQRecyclerAdapter.FAQRecyclerViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        LayoutInflater inflater = LayoutInflater.from(mContext);

        View faqView = inflater.inflate(R.layout.activity_faq_recyclerview)

    }
}