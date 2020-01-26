package com.applicationslab.ayurvedictreatment.activity;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.applicationslab.ayurvedictreatment.utility.PreferenceUtil;
import com.applicationslab.ayurvedictreatment.utility.Urls;
import com.applicationslab.ayurvedictreatment.utility.UtilityMethod;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;
import com.applicationslab.ayurvedictreatment.R;

import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by user on 6/18/2016.
 */
public class DiagnosisResultActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtDisease;
    Button btnSave;

    String disease = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis_result);
        initView();
        setUIClickHandler();
        initData();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Diagnosis Result");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtDisease = (TextView) findViewById(R.id.txtDisease);
        btnSave = (Button) findViewById(R.id.btnSave);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        btnSave.setTypeface(tfRegular, Typeface.BOLD);
        txtDisease.setTypeface(tfRegular, Typeface.BOLD);
    }


    private void setUIClickHandler() {
        btnSave.setOnClickListener(this);
    }


    private void initData() {
        disease = getIntent().getExtras().getString("disease");
        txtDisease.setText(disease);
    }

    private String getDate() {
        String date = "";
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        date = sdf.format(new Date());
        return date;
    }


    private void callAddDiseaseApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        String url= Urls.URL_ADD_DISEASE;
        StringRequest mRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mDialog.dismiss();
                    JSONObject json=new JSONObject(response);
                    int success=json.getInt("success");
                    if(success==1) {
                        new CustomToast(DiagnosisResultActivity.this, "Disease was saved successfully", "", false);
                        finish();
                    } else {
                        new CustomToast(DiagnosisResultActivity.this, "Disease can't be saved", "", false);
                    }
                }
                catch (Exception e)
                {
                    new CustomToast(DiagnosisResultActivity.this, "Something went wrong", "", false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                new CustomToast(DiagnosisResultActivity.this, "Something went wrong", "", false);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                PreferenceUtil preferenceUtil = new PreferenceUtil(DiagnosisResultActivity.this);
                params.put("username" , preferenceUtil.getUserName());
                params.put("date", getDate());
                params.put("disease", disease);
                return params;

            }
        };
        Volley.newRequestQueue(this).add(mRequest);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSave) {
            UtilityMethod utilityMethod = new UtilityMethod();
            if(utilityMethod.isConnectedToInternet(this)) {
                callAddDiseaseApi();
            } else {
                new CustomToast(this, "Internet connection is required", "", false);
            }
        }
    }

}
