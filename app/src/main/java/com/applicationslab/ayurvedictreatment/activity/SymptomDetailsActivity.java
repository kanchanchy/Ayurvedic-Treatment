package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;

/**
 * Created by user on 6/5/2016.
 */
public class SymptomDetailsActivity extends AppCompatActivity {

    TextView txtSymptom;
    TextView txtTreatment;
    TextView txtDose;
    TextView txtSuggestion;
    TextView txtSymptomLabel;
    TextView txtTreatmentLabel;
    TextView txtDoseLabel;
    TextView txtSuggestionLabel;

    String title = "";
    String symptom = "";
    String treatment = "";
    String dose = "";
    String suggestion = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_details);
        initData();
        initView();
        setContentData();
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
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtSymptom = (TextView) findViewById(R.id.txtSymptom);
        txtTreatment = (TextView) findViewById(R.id.txtTreatment);
        txtDose = (TextView) findViewById(R.id.txtDose);
        txtSuggestion = (TextView) findViewById(R.id.txtSuggestion);
        txtSymptomLabel = (TextView) findViewById(R.id.txtSymptomLabel);
        txtTreatmentLabel = (TextView) findViewById(R.id.txtTreatmentLabel);
        txtDoseLabel = (TextView) findViewById(R.id.txtDoseLabel);
        txtSuggestionLabel = (TextView) findViewById(R.id.txtSuggestionLabel);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtSymptom.setTypeface(tfRegular);
        txtTreatment.setTypeface(tfRegular);
        txtDose.setTypeface(tfRegular);
        txtSuggestion.setTypeface(tfRegular);
        txtSymptomLabel.setTypeface(tfRegular, Typeface.BOLD);
        txtTreatmentLabel.setTypeface(tfRegular, Typeface.BOLD);
        txtDoseLabel.setTypeface(tfRegular, Typeface.BOLD);
        txtSuggestionLabel.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void initData() {
        try {
            Intent intent = getIntent();
            title = intent.getExtras().getString("title");
            symptom = intent.getExtras().getString("symptom");
            treatment = intent.getExtras().getString("treatment");
            dose = intent.getExtras().getString("dose");
            suggestion = intent.getExtras().getString("suggestion");
        } catch (Exception e) {

        }
    }

    private void setContentData() {
        txtSymptom.setText(symptom);
        txtTreatment.setText(treatment);
        txtDose.setText(dose);
        txtSuggestion.setText(suggestion);
    }

}
