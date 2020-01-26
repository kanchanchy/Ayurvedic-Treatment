package com.applicationslab.ayurvedictreatment.adapter;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.activity.AboutAppActivity;
import com.applicationslab.ayurvedictreatment.activity.AboutDeveloperActivity;
import com.applicationslab.ayurvedictreatment.activity.BMICalculatorActivity;
import com.applicationslab.ayurvedictreatment.activity.DiagnosisActivity;
import com.applicationslab.ayurvedictreatment.activity.FeedbackActivity;
import com.applicationslab.ayurvedictreatment.activity.HerbalPlantOptionActivity;
import com.applicationslab.ayurvedictreatment.activity.LoginActivity;
import com.applicationslab.ayurvedictreatment.activity.MapsActivity;
import com.applicationslab.ayurvedictreatment.activity.PrescriptionRequestActivity;
import com.applicationslab.ayurvedictreatment.activity.SymptomListActivity;
import com.applicationslab.ayurvedictreatment.datamodel.OptionsData;
import com.applicationslab.ayurvedictreatment.utility.PreferenceUtil;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;

import java.util.ArrayList;


/**
 * Created by user on 5/9/2016.
 */
public class OptionsAdapter extends RecyclerView.Adapter<OptionsAdapter.MyViewHlder> {

    Activity mContext;
    ArrayList<OptionsData> optionsData;
    int selectedPos;


    public OptionsAdapter(Activity mContext, ArrayList<OptionsData> optionsData) {
        this.mContext = mContext;
        this.optionsData = optionsData;
    }

    @Override
    public MyViewHlder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.row_options, parent, false);
        return new MyViewHlder(view);
    }

    @Override
    public void onBindViewHolder(MyViewHlder holder, int position) {
        holder.txtName.setText(optionsData.get(position).getName());
        holder.txtDetails.setText(optionsData.get(position).getDetails());
        holder.imgViewLogo.setImageResource(optionsData.get(position).getImageId());
     /*   if(position == (optionsData.size()-1)) {
            holder.viewSeparator.setVisibility(View.GONE);
        } */
    }

    @Override
    public int getItemCount() {
        return optionsData.size();
    }

    public class MyViewHlder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ImageView imgViewLogo;
        TextView txtName;
        TextView txtDetails;
        RelativeLayout relativeContainer;
        View viewSeparator;
        private ProgressDialog progressDialog;

        public MyViewHlder(View itemView) {
            super(itemView);
            imgViewLogo = (ImageView) itemView.findViewById(R.id.imgViewLogo);
            txtName = (TextView) itemView.findViewById(R.id.txtName);
            txtDetails = (TextView) itemView.findViewById(R.id.txtDetails);
            relativeContainer = (RelativeLayout) itemView.findViewById(R.id.relativeContainer);
            viewSeparator = (View) itemView.findViewById(R.id.viewSeparator);
            relativeContainer.setOnClickListener(this);

            Typeface tfRegular = Typeface.createFromAsset(mContext.getAssets(), "fonts/OpenSansRegular.ttf");
            txtName.setTypeface(tfRegular);
            txtDetails.setTypeface(tfRegular);
        }

        @Override
        public void onClick(View v) {
            int pos = getAdapterPosition();
            selectedPos = pos;
            if(v.getId() == R.id.relativeContainer) {
                if(pos == 0) {
                    mContext.startActivity(new Intent(mContext, SymptomListActivity.class));
                }else if(pos == 1) {
                    PreferenceUtil preferenceUtil = new PreferenceUtil(mContext);
                    if("".equalsIgnoreCase(preferenceUtil.getUserName())) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.putExtra("target_job", "diagnosis");
                        mContext.startActivity(intent);
                    } else {
                        mContext.startActivity(new Intent(mContext, DiagnosisActivity.class));
                    }
                } else if(pos == 2) {
                    PreferenceUtil preferenceUtil = new PreferenceUtil(mContext);
                    if("".equalsIgnoreCase(preferenceUtil.getUserName())) {
                        Intent intent = new Intent(mContext, LoginActivity.class);
                        intent.putExtra("target_job", "prescription");
                        mContext.startActivity(intent);
                    } else {
                        mContext.startActivity(new Intent(mContext, PrescriptionRequestActivity.class));
                    }
                } else if(pos == 3) {
                    mContext.startActivity(new Intent(mContext, HerbalPlantOptionActivity.class));
                } else if(pos == 4) {
                    mContext.startActivity(new Intent(mContext, BMICalculatorActivity.class));
                } else if(pos == 5) {
                    if(isGooglePlayServicesAvailable(mContext)) {
                        mContext.startActivity(new Intent(mContext, MapsActivity.class));
                    }
                   // mContext.startActivity(new Intent(mContext, MapsActivity.class));
                } else if(pos == 6) {
                    mContext.startActivity(new Intent(mContext, FeedbackActivity.class));
                } else if(pos == 7) {
                    mContext.startActivity(new Intent(mContext, AboutAppActivity.class));
                } else if(pos == 8) {
                    mContext.startActivity(new Intent(mContext, AboutDeveloperActivity.class));
                }
            }
        }

    }



    public boolean isGooglePlayServicesAvailable(Activity activity) {
        GoogleApiAvailability googleApiAvailability = GoogleApiAvailability.getInstance();
        int status = googleApiAvailability.isGooglePlayServicesAvailable(activity);
        if(status != ConnectionResult.SUCCESS) {
            if(googleApiAvailability.isUserResolvableError(status)) {
                googleApiAvailability.getErrorDialog(activity, status, 2404).show();
            }
            return false;
        }
        return true;
    }



}
