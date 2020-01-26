package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.appdata.StaticData;

import java.util.ArrayList;

/**
 * Created by user on 6/8/2016.
 */
public class SuggestedHerbalPlantActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtOptional;
    TextView txtPlantName;
    Button btnPlantDetails;

    String title = "";
    ArrayList<Integer> plantPositions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_herbal_plant);
        initData();
        initView();
        setAllContentData();
        setUiClickHandler();
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
        try {
            title = getIntent().getExtras().getString("title");
            plantPositions = (ArrayList<Integer>) getIntent().getSerializableExtra("position");
        } catch (Exception e) {

        }
    }

    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtOptional = (TextView) findViewById(R.id.txtOptional);
        txtPlantName = (TextView) findViewById(R.id.txtPlantName);
        btnPlantDetails = (Button) findViewById(R.id.btnPlantDetails);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtOptional.setTypeface(tfRegular);
        txtPlantName.setTypeface(tfRegular);
        btnPlantDetails.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void setUiClickHandler() {
        btnPlantDetails.setOnClickListener(this);
    }

    private void setAllContentData() {
        if(plantPositions != null) {
            String text = "";
            for (int i = 0; i <plantPositions.size(); i++) {
                if(i == 0) {
                    text += StaticData.getHerbalPlantsDatas().get(plantPositions.get(i)).getName();
                } else {
                    text += ", " + StaticData.getHerbalPlantsDatas().get(i).getName();
                }
            }
            txtPlantName.setText(text);
        }
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnPlantDetails) {
            Intent intent = new Intent(SuggestedHerbalPlantActivity.this, SuggestedDetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("position", plantPositions);
            startActivity(intent);
        }
    }

}
