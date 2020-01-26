package com.applicationslab.ayurvedictreatment.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;

/**
 * Created by user on 6/6/2016.
 */
public class AboutDeveloperActivity extends AppCompatActivity {

    TextView txtAppName;
    TextView txtOptional;
    TextView txtDeveloperName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_developer);
        initView();
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
        getSupportActionBar().setTitle("About Developer");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtAppName = (TextView) findViewById(R.id.txtAppName);
        txtOptional = (TextView) findViewById(R.id.txtOptional);
        txtDeveloperName = (TextView) findViewById(R.id.txtDeveloperName);

        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/ROADMOVIE.ttf");
        txtAppName.setTypeface(typeface);
        txtOptional.setTypeface(typeface);
        txtDeveloperName.setTypeface(typeface);
    }


}
