package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.utility.PreferenceUtil;

/**
 * Created by user on 6/6/2016.
 */
public class PrescriptionRequestActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtOptional, txtAppendicites, txtAsthma, txtBronchitis, txtDiabetes, txtDysentery, txtHeatDisease, txtBloodPressure, txtJaundice, txtMalaria, txtMumps;
    RelativeLayout relativeAppendicites, relativeAsthma, relativeBronchitis, relativeDiabetes, relativeDysentery, relativeHeatDisease, relativeBloodPressure, relativeJaundice, relativeMalaria, relativeMumps;

    Typeface tfRegular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_request);
        initView();
        setUIClickHandler();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        } else if(item.getItemId() == R.id.action_logout) {
            showLogoutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Prescription Request");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtOptional = (TextView) findViewById(R.id.txtOptional);
        txtAppendicites = (TextView) findViewById(R.id.txtAppendicites);
        txtAsthma = (TextView) findViewById(R.id.txtAsthma);
        txtBronchitis = (TextView) findViewById(R.id.txtBronchitis);
        txtDiabetes = (TextView) findViewById(R.id.txtDiabetes);
        txtDysentery = (TextView) findViewById(R.id.txtDysentery);
        txtHeatDisease = (TextView) findViewById(R.id.txtHeartDisease);
        txtBloodPressure = (TextView) findViewById(R.id.txtBloodPressure);
        txtJaundice = (TextView) findViewById(R.id.txtJaundice);
        txtMalaria = (TextView) findViewById(R.id.txtMalaria);
        txtMumps = (TextView) findViewById(R.id.txtMumps);
        relativeAppendicites = (RelativeLayout) findViewById(R.id.relativeAppendicites);
        relativeAsthma = (RelativeLayout) findViewById(R.id.relativeAsthma);
        relativeBronchitis = (RelativeLayout) findViewById(R.id.relativeBronchitis);
        relativeDiabetes = (RelativeLayout) findViewById(R.id.relativeDiabates);
        relativeDysentery = (RelativeLayout) findViewById(R.id.relativeDysentry);
        relativeHeatDisease = (RelativeLayout) findViewById(R.id.relativeHeartDisease);
        relativeBloodPressure = (RelativeLayout) findViewById(R.id.relativeBloodPressure);
        relativeJaundice = (RelativeLayout) findViewById(R.id.relativeJaundice);
        relativeMalaria = (RelativeLayout) findViewById(R.id.relativeMalaria);
        relativeMumps = (RelativeLayout) findViewById(R.id.relativeMumps);

        tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtOptional.setTypeface(tfRegular);
        txtAppendicites.setTypeface(tfRegular);
        txtAsthma.setTypeface(tfRegular);
        txtBronchitis.setTypeface(tfRegular);
        txtDiabetes.setTypeface(tfRegular);
        txtDysentery.setTypeface(tfRegular);
        txtHeatDisease.setTypeface(tfRegular);
        txtBloodPressure.setTypeface(tfRegular);
        txtJaundice.setTypeface(tfRegular);
        txtMalaria.setTypeface(tfRegular);
        txtMumps.setTypeface(tfRegular);
    }

    private void setUIClickHandler() {
        relativeAppendicites.setOnClickListener(this);
        relativeAsthma.setOnClickListener(this);
        relativeBronchitis.setOnClickListener(this);
        relativeDiabetes.setOnClickListener(this);
        relativeDysentery.setOnClickListener(this);
        relativeHeatDisease.setOnClickListener(this);
        relativeBloodPressure.setOnClickListener(this);
        relativeJaundice.setOnClickListener(this);
        relativeMalaria.setOnClickListener(this);
        relativeMumps.setOnClickListener(this);
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_warning, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView txtWarning = (TextView) view.findViewById(R.id.txtWarning);
        TextView txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        TextView txtAccept = (TextView) view.findViewById(R.id.txtAccept);
        TextView txtCancel = (TextView) view.findViewById(R.id.txtCancel);

        txtWarning.setTypeface(tfRegular);
        txtMessage.setTypeface(tfRegular);
        txtAccept.setTypeface(tfRegular, Typeface.BOLD);
        txtCancel.setTypeface(tfRegular, Typeface.BOLD);

        txtMessage.setText("Are you sure you want to logout?");
        txtAccept.setText("LOGOUT");
        txtCancel.setText("CANCEL");

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        txtAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                makeLogout();
            }
        });
    }

    private void makeLogout() {
        PreferenceUtil preferenceUtil = new PreferenceUtil(this);
        preferenceUtil.clearPreference();
        Intent intent = new Intent(PrescriptionRequestActivity.this, LoginActivity.class);
        intent.putExtra("target_job", "prescription");
        startActivity(intent);
        finish();
    }

    @Override
    public void onClick(View v) {
        String disease = "";
        if(v.getId() == R.id.relativeAppendicites) {
            disease = "Appendicitis";
        } else if(v.getId() == R.id.relativeAsthma) {
            disease = "Asthma";
        } else if(v.getId() == R.id.relativeBronchitis) {
            disease = "Bronchitis";
        } else if(v.getId() == R.id.relativeDiabates) {
            disease = "Diabetes";
        } else if(v.getId() == R.id.relativeDysentry) {
            disease = "Dysentery";
        } else if(v.getId() == R.id.relativeHeartDisease) {
            disease = "Heart Disease";
        } else if(v.getId() == R.id.relativeBloodPressure) {
            disease = "High Blood Pressure";
        } else if(v.getId() == R.id.relativeJaundice) {
            disease = "Jaundice";
        } else if(v.getId() == R.id.relativeMalaria) {
            disease = "Malaria";
        } else if(v.getId() == R.id.relativeMumps) {
            disease = "Mumps";
        }
        if(!"".equalsIgnoreCase(disease)) {
            Intent intent = new Intent(PrescriptionRequestActivity.this, PrescriptionDetailsActivity.class);
            intent.putExtra("disease", disease);
            startActivity(intent);
        }
    }

}
