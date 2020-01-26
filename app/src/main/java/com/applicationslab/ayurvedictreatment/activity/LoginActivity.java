package com.applicationslab.ayurvedictreatment.activity;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
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

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by user on 6/7/2016.
 */
public class LoginActivity extends AppCompatActivity implements View.OnClickListener, TextView.OnEditorActionListener{

    TextView txtOptional;
    TextView txtUserName;
    TextView txtPassword;
    TextView txtRegister;
    EditText edtUserName;
    EditText edtPassword;
    Button btnLogin;
    Button btnRegister;

    String targetJob = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
            finish();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initData() {
        targetJob = getIntent().getExtras().getString("target_job");
    }

    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Login");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtOptional = (TextView) findViewById(R.id.txtOptional);
        txtUserName = (TextView) findViewById(R.id.txtUserName);
        txtPassword = (TextView) findViewById(R.id.txtPassword);
        txtRegister = (TextView) findViewById(R.id.txtRegister);
        edtUserName = (EditText) findViewById(R.id.edtUserName);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnRegister = (Button) findViewById(R.id.btnRegister);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtOptional.setTypeface(tfRegular);
        txtUserName.setTypeface(tfRegular);
        txtPassword.setTypeface(tfRegular);
        txtRegister.setTypeface(tfRegular);
        edtUserName.setTypeface(tfRegular);
        edtPassword.setTypeface(tfRegular);
        btnLogin.setTypeface(tfRegular, Typeface.BOLD);
        btnRegister.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void setUIClickHandler() {
        btnLogin.setOnClickListener(this);
        btnRegister.setOnClickListener(this);
        edtPassword.setOnEditorActionListener(this);
    }

    private void hideKeyboard() {
        View v = getCurrentFocus();
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }
    }


    private void callLoginApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        String url= Urls.URL_LOGIN;
        StringRequest mRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mDialog.dismiss();
                    JSONObject json=new JSONObject(response);
                    int success=json.getInt("success");
                    if(success==1)
                    {
                        String email=json.getString("email");
                        makeLogin(email);
                    }
                    else new CustomToast(LoginActivity.this, "User not found", "", false);
                }
                catch (Exception e)
                {
                    new CustomToast(LoginActivity.this, "Something went wrong", "", false);
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                new CustomToast(LoginActivity.this, "Something went wrong", "", false);
            }
        })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String>  params = new HashMap<>();
                params.put("username" , edtUserName.getText().toString().trim());
                params.put("password", edtPassword.getText().toString().trim());
                return params;

            }
        };
        Volley.newRequestQueue(this).add(mRequest);
    }


    private void makeLogin(String email) {
        PreferenceUtil preferenceUtil = new PreferenceUtil(this);
        preferenceUtil.setUserName(edtUserName.getText().toString().trim());
        preferenceUtil.setEmail(email);
        preferenceUtil.setPassword(edtPassword.getText().toString().trim());

        if(targetJob.equalsIgnoreCase("diagnosis")) {
            startActivity(new Intent(LoginActivity.this, DiagnosisActivity.class));
            finish();
        } else if(targetJob.equalsIgnoreCase("prescription")) {
            startActivity(new Intent(LoginActivity.this, PrescriptionRequestActivity.class));
            finish();
        }
    }

    private boolean isInputValid() {
        if(edtUserName.getText().toString() == null || edtUserName.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "User name is required", "", false);
            return false;
        }
        if(edtPassword.getText().toString() == null || edtPassword.getText().toString().trim().equalsIgnoreCase("")) {
            new CustomToast(this, "Password is required", "", false);
            return false;
        }
        return true;
    }

    private void onLoginClicked() {
        if(isInputValid()) {
            UtilityMethod  utilityMethod = new UtilityMethod();
            if(utilityMethod.isConnectedToInternet(this)) {
                callLoginApi();
            } else {
                new CustomToast(this, "Internet connection is required", "", false);
            }
        }
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(v.getId() == R.id.edtPassword) {
            if(actionId == EditorInfo.IME_ACTION_GO) {
                hideKeyboard();
                onLoginClicked();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnRegister) {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            intent.putExtra("target_job", targetJob);
            startActivity(intent);
            finish();
        } else if(v.getId() == R.id.btnLogin) {
            onLoginClicked();
        }
    }

}
