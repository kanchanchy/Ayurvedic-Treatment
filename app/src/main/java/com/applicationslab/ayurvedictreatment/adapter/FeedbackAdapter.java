package com.applicationslab.ayurvedictreatment.adapter;

import android.app.Activity;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.datamodel.FeedbackData;

import java.util.ArrayList;

/**
 * Created by user on 5/9/2016.
 */
public class FeedbackAdapter extends RecyclerView.Adapter<FeedbackAdapter.MyViewHlder> {

    Activity mContext;
    ArrayList<FeedbackData> feedbackDatas;


    public FeedbackAdapter(Activity mContext, ArrayList<FeedbackData> feedbackDatas) {
        this.mContext = mContext;
        this.feedbackDatas = feedbackDatas;
    }

    @Override
    public MyViewHlder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_feedback, parent, false);
        return new MyViewHlder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHlder holder, int position) {
        holder.txtName.setText(feedbackDatas.get(position).getName());
        holder.txtDetails.setText(feedbackDatas.get(position).getDetails());
    }

    @Override
    public int getItemCount() {
        return feedbackDatas.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder{

        TextView txtName;
        TextView txtDetails;

        public MyViewHlder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDetails = (TextView) itemView.findViewById(R.id.txtDetails);

            Typeface tfRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSansRegular.ttf");
            txtName.setTypeface(tfRegular);
            txtDetails.setTypeface(tfRegular);
        }

    }




}
