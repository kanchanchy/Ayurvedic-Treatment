package com.applicationslab.ayurvedictreatment.adapter;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.activity.HerbalPlantDetailsActivity;
import com.applicationslab.ayurvedictreatment.datamodel.HerbalPlantsData;

import java.util.ArrayList;


/**
 * Created by user on 5/9/2016.
 */
public class HerbalPlantsAdapter extends RecyclerView.Adapter<HerbalPlantsAdapter.MyViewHlder> {

    Activity mContext;
    ArrayList<HerbalPlantsData> herbalPlantsData;


    public HerbalPlantsAdapter(Activity mContext, ArrayList<HerbalPlantsData> herbalPlantsData) {
        this.mContext = mContext;
        this.herbalPlantsData = herbalPlantsData;
    }

    @Override
    public MyViewHlder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_herbal_plants, parent, false);
        return new MyViewHlder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHlder holder, int position) {
        holder.txtName.setText(herbalPlantsData.get(position).getName());
        holder.imgViewLogo.setImageResource(herbalPlantsData.get(position).getImageId());
    }

    @Override
    public int getItemCount() {
        return herbalPlantsData.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgViewLogo;
        TextView txtName;
        RelativeLayout relativeContainer;

        public MyViewHlder(View itemView) {
            super(itemView);
            imgViewLogo = (ImageView) itemView.findViewById(R.id.imgViewLogo);
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
                Intent intent = new Intent(mContext, HerbalPlantDetailsActivity.class);
                intent.putExtra("title", herbalPlantsData.get(pos).getName());
                intent.putExtra("scientific_name", herbalPlantsData.get(pos).getScientificName());
                intent.putExtra("description", herbalPlantsData.get(pos).getDetails());
                intent.putExtra("uses", herbalPlantsData.get(pos).getUses());
                intent.putExtra("img_id", herbalPlantsData.get(pos).getImageId());
                mContext.startActivity(intent);
            }
        }

    }




}
