package com.applicationslab.ayurvedictreatment.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
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
import com.applicationslab.ayurvedictreatment.utility.PreferenceUtil;
import com.applicationslab.ayurvedictreatment.utility.Urls;
import com.applicationslab.ayurvedictreatment.utility.UtilityMethod;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by user on 6/7/2016.
 */
public class RegisterActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener{

    TextView txtUserName;
    TextView txtEmail;
    TextView txtPassword;
    TextView txtConfirmPass;
    EditText edtUserName;
    EditText edtEmail;
    EditText edtPassword;
    EditText edtConfirmPass;
    Button btnSubmit;

    String targetJob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initData();
        initView();
        setUIClickHandler();
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


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
            intent.putExtra("target_job", targetJob);
            startActivity(intent);
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
        intent.putExtra("target_job", targetJob);
        startActivity(intent);
        finish();
    }

    private void initData() {
        targetJob = getIntent().getExtras().getString("target_job");
    }

    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Registration");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtEmail = (TextView) findViewById(R.id.txtEmail);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtConfirmPass = (TextView) findViewById(R.id.txtConfirmPass);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPass = (EditText) findViewById(R.id.edtConfirmPass);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtUserName.setTypeface(tfRegular);
        txtEmail.setTypeface(tfRegular);
        txtPassword.setTypeface(tfRegular);
        txtConfirmPass.setTypeface(tfRegular);
        edtUserName.setTypeface(tfRegular);
        edtEmail.setTypeface(tfRegular);
        edtPassword.setTypeface(tfRegular);
        edtConfirmPass.setTypeface(tfRegular);
        btnSubmit.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void setUIClickHandler() {
        btnSubmit.setOnClickListener(this);
        edtConfirmPass.setOnEditorActionListener(this);
    }

    private void hideKeyboard() {
        View v = getCurrentFocus();
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }

    private void callRegistrationApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        String url= Urls.URL_REGISTER;
        StringRequest mRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    Log.e("response_reg", response);
                    mDialog.dismiss();
                    JSONObject json=new JSONObject(response);
                    int success=json.getInt("success");
                    if(success==1)
                    {
                        new CustomToast(RegisterActivity.this, "Registration successfull", "", true);
                        makeRegister();
                    } else if(success == 2) {
                        new CustomToast(RegisterActivity.this, "User name already exists", "", false);
                    } else if(success == 3) {
                        new CustomToast(RegisterActivity.this, "Email already exists", "", false);
                    } else {
                        new CustomToast(RegisterActivity.this, "Registration failed", "", false);
                    }
                }
                catch (Exception e)
                {
                    new CustomToast(RegisterActivity.this, "Something went wrong " + e, "", false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                new CustomToast(RegisterActivity.this, "Something went wrong " + error, "", false);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("username" , edtUserName.getText().toString().trim());
                params.put("email" , edtEmail.getText().toString().trim());
                params.put("password", edtPassword.getText().toString().trim());
                return params;

            }
        };
        Volley.newRequestQueue(this).add(mRequest);
    }

    private void makeRegister() {
        PreferenceUtil preferenceUtil = new PreferenceUtil(this);
        preferenceUtil.setUserName(edtUserName.getText().toString().trim());
        preferenceUtil.setEmail(edtEmail.getText().toString().trim());
        preferenceUtil.setPassword(edtPassword.getText().toString().trim());

        if(targetJob.equalsIgnoreCase("diagnosis")) {
            startActivity(new Intent(RegisterActivity.this, DiagnosisActivity.class));
            finish();
        } else if(targetJob.equalsIgnoreCase("prescription")) {
            startActivity(new Intent(RegisterActivity.this, PrescriptionRequestActivity.class));
            finish();
        }
    }

    private boolean isInputValid() {
        if(edtUserName.getText().toString() == null || edtUserName.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "User name is required", "", false);
            return false;
        }
        if(edtEmail.getText().toString() == null || edtEmail.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "Email address is required", "", false);
            return false;
        }
        UtilityMethod utilityMethod = new UtilityMethod();
        if(!utilityMethod.isEmailValid(edtEmail.getText().toString().trim())) {
            new CustomToast(this, "Email address is invalid", "", false);
            return false;
        }
        if(edtPassword.getText().toString() == null || edtPassword.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "Password is required", "", false);
            return false;
        }
        if(edtConfirmPass.getText().toString() == null || edtConfirmPass.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "Confirm password is required", "", false);
            return false;
        }
        if(!edtPassword.getText().toString().trim().equalsIgnoreCase(edtConfirmPass.getText().toString().trim())) {
            new CustomToast(this, "Password and confirm password are not matched", "", false);
            return false;
        }
        return true;
    }

    private void onRegisterClicked() {
        if(isInputValid()) {
            UtilityMethod  utilityMethod = new UtilityMethod();
            if(utilityMethod.isConnectedToInternet(this)) {
                callRegistrationApi();
            } else {
                new CustomToast(this, "Internet connection is required", "", false);
            }
        }
    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(v.getId() == R.id.edtConfirmPass) {
            if(actionId == EditorInfo.IME_ACTION_GO) {
                hideKeyboard();
                onRegisterClicked();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSubmit) {
            onRegisterClicked();
        }
    }

}
