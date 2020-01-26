package com.applicationslab.ayurvedictreatment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.activity.SuggestedHerbalPlantActivity;
import com.applicationslab.ayurvedictreatment.datamodel.DiseaseSearchData;

import java.util.ArrayList;


/**
 * Created by user on 5/9/2016.
 */
public class DiseaseAdapter extends RecyclerView.Adapter<DiseaseAdapter.MyViewHlder> {

    Activity mContext;
    ArrayList<DiseaseSearchData> diseaseSearchData;


    public DiseaseAdapter(Activity mContext, ArrayList<DiseaseSearchData> diseaseSearchData) {
        this.mContext = mContext;
        this.diseaseSearchData = diseaseSearchData;
    }

    @Override
    public MyViewHlder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_diseases, parent, false);
        return new MyViewHlder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHlder holder, int position) {
        holder.txtName.setText(diseaseSearchData.get(position).getDiseaseName());
    }

    @Override
    public int getItemCount() {
        return diseaseSearchData.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder implements View.OnClickListener{

        TextView txtName;
        RelativeLayout relativeContainer;

        public MyViewHlder(View itemView) {
            super(itemView);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            relativeContainer = (RelativeLayout) itemView.findViewById(R.id.relativeContainer);
            relativeContainer.setOnClickListener(this);

            Typeface tfRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSansRegular.ttf");
            txtName.setTypeface(tfRegular);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            if(v.getId() == R.id.relativeContainer) {
                Intent intent = new Intent(mContext, SuggestedHerbalPlantActivity.class);
                intent.putExtra("title", diseaseSearchData.get(pos).getDiseaseName());
                intent.putExtra("position", diseaseSearchData.get(pos).getPlantPositions());
                mContext.startActivity(intent);
            }
        }

    }




}
