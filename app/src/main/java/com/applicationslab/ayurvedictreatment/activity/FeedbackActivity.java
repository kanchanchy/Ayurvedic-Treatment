package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.applicationslab.ayurvedictreatment.R;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by user on 6/6/2016.
 */
public class FeedbackActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnViewFeedback;
    Button btnSubmitFeedback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);
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


    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Feedback");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        btnViewFeedback = (Button) findViewById(R.id.btnViewFeedback);
        btnSubmitFeedback = (Button) findViewById(R.id.btnSubmitFeedback);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        btnViewFeedback.setTypeface(tfRegular, Typeface.BOLD);
        btnSubmitFeedback.setTypeface(tfRegular, Typeface.BOLD);
    }


    private void setUIClickHandler() {
        btnViewFeedback.setOnClickListener(this);
        btnSubmitFeedback.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnViewFeedback) {
            startActivity(new Intent(this, ViewFeedbackActivity.class));
        } else if(v.getId() == R.id.btnSubmitFeedback) {
            startActivity(new Intent(this, SubmitFeedbackActivity.class));
        }
    }

}
