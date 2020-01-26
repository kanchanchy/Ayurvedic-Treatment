package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by user on 6/5/2016.
 */
public class HerbalPlantDetailsActivity extends AppCompatActivity {

    TextView txtScientificLabel;
    TextView txtDescriptionLabel;
    TextView txtUsesLabel;
    TextView txtScientificName;
    TextView txtDescription;
    TextView txtUses;
    ImageView imgViewHerbalPlant;

    String title = "";
    String scientificName = "";
    String description = "";
    String uses = "";
    int imgId = R.drawable.herbal_plant;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_herbal_plant_details);
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
        Toolbar toolBar=(Toolbar) findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle(title);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtScientificLabel = (TextView) findViewById(R.id.txtScientificLabel);
        txtDescriptionLabel = (TextView) findViewById(R.id.txtDescriptionLabel);
        txtUsesLabel = (TextView) findViewById(R.id.txtUsesLabel);
        txtScientificName = (TextView) findViewById(R.id.txtScientificName);
        txtDescription = (TextView) findViewById(R.id.txtDescription);
        txtUses = (TextView) findViewById(R.id.txtUses);
        imgViewHerbalPlant = (ImageView) findViewById(R.id.imgViewHerbalPlant);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtScientificName.setTypeface(tfRegular, Typeface.ITALIC);
        txtDescription.setTypeface(tfRegular);
        txtUses.setTypeface(tfRegular);
        txtScientificLabel.setTypeface(tfRegular, Typeface.BOLD);
        txtDescriptionLabel.setTypeface(tfRegular, Typeface.BOLD);
        txtUsesLabel.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void initData() {
        try {
            Intent intent = getIntent();
            title = intent.getExtras().getString("title");
            scientificName = intent.getExtras().getString("scientific_name");
            description = intent.getExtras().getString("description");
            uses = intent.getExtras().getString("uses");
            imgId = intent.getExtras().getInt("img_id");
        } catch (Exception e) {

        }
    }

    private void setContentData() {
        txtDescription.setText(description);
        txtUses.setText(uses);
        imgViewHerbalPlant.setImageResource(imgId);
        if(!"".equalsIgnoreCase(scientificName)) {
            txtScientificName.setText(scientificName);
        } else {
            txtScientificLabel.setVisibility(View.GONE);
            txtScientificName.setVisibility(View.GONE);
        }
    }

}
