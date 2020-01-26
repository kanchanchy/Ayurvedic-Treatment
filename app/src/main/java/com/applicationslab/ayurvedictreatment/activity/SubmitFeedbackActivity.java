package com.applicationslab.ayurvedictreatment.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.utility.Urls;
import com.applicationslab.ayurvedictreatment.utility.UtilityMethod;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 6/18/2016.
 */
public class SubmitFeedbackActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtOptional;
    TextView txtUserName;
    TextView txtFeedback;
    EditText edtUserName;
    EditText edtFeedback;
    Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submit_feedback);
        initView();
        setUIClickHandler();
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if ( v instanceof EditText) {
                Rect outRect = new Rect();
                v.getGlobalVisibleRect(outRect);
                if (!outRect.contains((int)event.getRawX(), (int)event.getRawY())) {
                    v.clearFocus();
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
        }
        return super.dispatchTouchEvent(event);
    }


    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Submit Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtOptional = (TextView) findViewById(R.id.txtOptional);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtFeedback = (TextView) findViewById(R.id.txtFeedback);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtFeedback = (EditText) findViewById(R.id.edtFeedback);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtOptional.setTypeface(tfRegular);
        txtUserName.setTypeface(tfRegular);
        txtFeedback.setTypeface(tfRegular);
        edtUserName.setTypeface(tfRegular);
        edtFeedback.setTypeface(tfRegular);
        btnSubmit.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void setUIClickHandler() {
        btnSubmit.setOnClickListener(this);
    }

    private void callSubmitFeedbackApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        String url= Urls.URL_GIVE_FEEDBACK;
        StringRequest mRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mDialog.dismiss();
                    JSONObject json=new JSONObject(response);
                    int success=json.getInt("success");
                    if(success==1) {
                        edtUserName.setText("");
                        edtFeedback.setText("");
                        new CustomToast(SubmitFeedbackActivity.this, "Feedback submitted successfully", "", false);
                    } else if(success == 2) {
                        new CustomToast(SubmitFeedbackActivity.this, "User name not found", "", false);
                    } else {
                        new CustomToast(SubmitFeedbackActivity.this, "Feedback submission failed", "", false);
                    }
                }
                catch (Exception e)
                {
                    new CustomToast(SubmitFeedbackActivity.this, "Something went wrong", "", false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                new CustomToast(SubmitFeedbackActivity.this, "Something went wrong", "", false);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("username" , edtUserName.getText().toString().trim());
                params.put("comment", edtFeedback.getText().toString().trim());
                return params;

            }
        };
        Volley.newRequestQueue(this).add(mRequest);
    }

    private boolean isInputValid() {
        if(edtUserName.getText().toString() == null || edtUserName.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "User name is required", "", false);
            return false;
        }
        if(edtFeedback.getText().toString() == null || edtFeedback.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "Feedback is required", "", false);
            return false;
        }
        return true;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSubmit) {
            if(isInputValid()) {
                UtilityMethod utilityMethod = new UtilityMethod();
                if(utilityMethod.isConnectedToInternet(this)) {
                    callSubmitFeedbackApi();
                } else {
                    new CustomToast(this, "Internet connection is required", "", false);
                }
            }
        }
    }

}
