package com.applicationslab.ayurvedictreatment.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.utility.UtilityMethod;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;
import com.applicationslab.ayurvedictreatment.R;

import java.text.DecimalFormat;

/**
 * Created by user on 6/4/2016.
 */
public class BMICalculatorActivity extends AppCompatActivity implements TextView.OnEditorActionListener, View.OnClickListener{

    TextView txtWeight;
    TextView txtHeightFeet;
    TextView txtHeightInches;
    EditText edtWeight;
    EditText edtHeightFeet;
    EditText edtHeightInches;
    Button btnCalculateBMI;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calculator);
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


    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("BMI Calculator");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtWeight = (TextView) findViewById(R.id.txtWeight);
        txtHeightFeet = (TextView) findViewById(R.id.txtHeightFeet);
        txtHeightInches = (TextView) findViewById(R.id.txtHeightInch);
        edtWeight = (EditText) findViewById(R.id.edtWeight);
        edtHeightFeet = (EditText) findViewById(R.id.edtHeightFeet);
        edtHeightInches = (EditText) findViewById(R.id.edtHeightInch);
        btnCalculateBMI = (Button) findViewById(R.id.btnCalculateBMI);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtWeight.setTypeface(tfRegular);
        txtHeightFeet.setTypeface(tfRegular);
        txtHeightInches.setTypeface(tfRegular);
        edtWeight.setTypeface(tfRegular);
        edtHeightFeet.setTypeface(tfRegular);
        edtHeightInches.setTypeface(tfRegular);
        btnCalculateBMI.setTypeface(tfRegular, Typeface.BOLD);

    }

    private void setUiClickHandler() {
        edtHeightFeet.setOnEditorActionListener(this);
        edtHeightInches.setOnEditorActionListener(this);
        btnCalculateBMI.setOnClickListener(this);
    }

    private boolean isDataValid() {
        if(edtWeight.getText().toString() == null || "".equals(edtWeight.getText().toString().trim())) {
            new CustomToast(this, "Weight is required", "", false);
            return false;
        }
        if(edtHeightFeet.getText().toString() == null || "".equals(edtHeightFeet.getText().toString().trim())) {
            new CustomToast(this, "Height is required", "", false);
            return false;
        }
        UtilityMethod utilityMethod = new UtilityMethod();
        if(!utilityMethod.isDecimalNumberValid(edtWeight.getText().toString().trim())) {
            new CustomToast(this, "Invalid weight found", "", false);
            return false;
        }
        if(!utilityMethod.isDecimalNumberValid(edtHeightFeet.getText().toString().trim())) {
            new CustomToast(this, "Invalid feet value found", "", false);
            return false;
        }
        if(!TextUtils.isEmpty(edtHeightInches.getText().toString().trim())) {
            if(!utilityMethod.isDecimalNumberValid(edtHeightInches.getText().toString().trim())) {
                new CustomToast(this, "Invalid inches value found", "", false);
                return false;
            }
        }
        return true;
    }

    private double getBmiValue(double weight, double height) {
        double bmiValue = (double)(weight/(double)(height*height));
        return bmiValue;
    }

    private void onSelectedCalculateBMI() {
        if(isDataValid()) {
            double weight = Double.valueOf(edtWeight.getText().toString().trim());
            double height = Double.valueOf(edtHeightFeet.getText().toString().trim())*12.0;
            String heightInchStr = edtHeightInches.getText().toString().trim();
            if(!TextUtils.isEmpty(heightInchStr)) {
                height += Double.valueOf(heightInchStr);
            }
            height = height* 0.0254;
            double bmiValueUnformatted = getBmiValue(weight, height);
            DecimalFormat df = new DecimalFormat("#.00");
            double bmiValue = Double.valueOf(df.format(bmiValueUnformatted));
            String status = "";
            int bmiCase;
            if(bmiValue < 18) {
                status = "You are underweight. Try to increase some weight.";
                bmiCase = 1;
            } else if(bmiValue < 18.5) {
                status = "You are thin for your height. Try to increase some weight.";
                bmiCase = 1;
            } else if(bmiValue < 25) {
                status = "You are normal. So keep up your regularity.";
                bmiCase = 2;
            } else if(bmiValue < 30) {
                status = "You are overweight. Try to loose some weight.";
                bmiCase = 3;
            } else {
                status = "You are obese. Consider consulting a doctor or loosing weight.";
                bmiCase = 3;
            }
            showBMIStatusDialog(bmiValue, status, bmiCase);
        }
    }


    private void showBMIStatusDialog(double value, String status, final int bmiCase) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_bmi_status, null);
        TextView txtDialogTitle = (TextView) view.findViewById(R.id.txtDialogTitle);
        TextView txtOptional = (TextView) view.findViewById(R.id.txtOptional);
        TextView txtBMIValue = (TextView) view.findViewById(R.id.txtBMIValue);
        TextView txtBMIStatus = (TextView) view.findViewById(R.id.txtBMIStatus);
        Button btnCancel = (Button) view.findViewById(R.id.btnCancel);
        Button btnSuggestions = (Button) view.findViewById(R.id.btnSuggestions);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtDialogTitle.setTypeface(tfRegular, Typeface.BOLD);
        txtOptional.setTypeface(tfRegular);
        txtBMIValue.setTypeface(tfRegular, Typeface.BOLD);
        txtBMIStatus.setTypeface(tfRegular);
        btnCancel.setTypeface(tfRegular, Typeface.BOLD);
        btnSuggestions.setTypeface(tfRegular, Typeface.BOLD);

        txtBMIValue.setText("" + value);
        txtBMIStatus.setText(status);
        builder.setView(view);
        builder.setCancelable(true);
        final AlertDialog dialog = builder.create();
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        btnSuggestions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(BMICalculatorActivity.this, BmiSuggestionsActivity.class);
                intent.putExtra("bmi_case", bmiCase);
                startActivity(intent);
            }
        });

        dialog.show();
    }


    private void hideKeyboard() {
        View v = getCurrentFocus();
        if (v != null) {
            InputMethodManager inputMethodManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
        }

    }


    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if(v.getId() == R.id.edtHeightInch) {
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                hideKeyboard();
                onSelectedCalculateBMI();
                return true;
            }
        }
        return false;
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnCalculateBMI) {
            onSelectedCalculateBMI();
        }
    }


}
