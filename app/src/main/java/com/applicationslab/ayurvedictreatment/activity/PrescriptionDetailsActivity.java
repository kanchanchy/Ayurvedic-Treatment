package com.applicationslab.ayurvedictreatment.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.net.Uri;
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

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.utility.PreferenceUtil;
import com.applicationslab.ayurvedictreatment.utility.UtilityMethod;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;

/**
 * Created by user on 6/7/2016.
 */
public class PrescriptionDetailsActivity extends AppCompatActivity implements View.OnClickListener {

    TextView txtAge;
    TextView txtProbDetails;
    EditText edtAge;
    EditText edtProbDetails;
    Button btnSubmit;

    String disease = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_prescription_details);
        initData();
        initView();
        setUiClickHandler();
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
        disease = getIntent().getExtras().getString("disease");
    }

    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Prescription for " + disease);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtAge = (TextView) findViewById(R.id.txtAge);
        txtProbDetails = (TextView) findViewById(R.id.txtProbDetails);
        edtAge = (EditText) findViewById(R.id.edtAge);
        edtProbDetails = (EditText) findViewById(R.id.edtProbDetails);
        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtAge.setTypeface(tfRegular);
        txtProbDetails.setTypeface(tfRegular);
        edtAge.setTypeface(tfRegular);
        edtProbDetails.setTypeface(tfRegular);
        btnSubmit.setTypeface(tfRegular, Typeface.BOLD);

    }

    private void setUiClickHandler() {
        btnSubmit.setOnClickListener(this);
    }


    private boolean isDataValid() {
        if(edtAge.getText().toString() == null || "".equals(edtAge.getText().toString().trim())) {
            new CustomToast(this, "Age is required", "", false);
            return false;
        }
        if(edtProbDetails.getText().toString() == null || "".equals(edtProbDetails.getText().toString().trim())) {
            new CustomToast(this, "Problem details is required", "", false);
            return false;
        }
        UtilityMethod utilityMethod = new UtilityMethod();
        if(!utilityMethod.isDecimalNumberValid(edtAge.getText().toString().trim())) {
            new CustomToast(this, "Invalid age found", "", false);
            return false;
        }
        return true;
    }


    protected void sendEmail() {
        PreferenceUtil preferenceUtil = new PreferenceUtil(this);
        String patientMail = preferenceUtil.getEmail();
        String body = "";
        body += "Patient's Email: " + patientMail + "\n";
        body += "Detected Disease: " + disease + "\n";
        body += "Patient's Age: " + edtAge.getText().toString().trim() + "\n";
        body += "Patient's Problem: " + edtProbDetails.getText().toString().trim() +"\n\n";
        body += "Please send proper prescription to the patient's email address. Thank you.";

        String[] TO = {EMAIL_ADDRESS_OF_DOCTOR};
        String[] CC = {};
        Intent emailIntent = new Intent(Intent.ACTION_SEND);

        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
      //  emailIntent.putExtra(Intent.EXTRA_CC, CC);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Ayurvedic Treatment");
        emailIntent.putExtra(Intent.EXTRA_TEXT, body);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send mail..."));
        }
        catch (android.content.ActivityNotFoundException ex) {
            new CustomToast(this, "There is no email client installed.", "", false);
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSubmit) {
            if(isDataValid()) {
                sendEmail();
            }
        }
    }

}
