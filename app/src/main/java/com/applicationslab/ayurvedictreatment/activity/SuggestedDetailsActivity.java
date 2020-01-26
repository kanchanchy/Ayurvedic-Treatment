package com.applicationslab.ayurvedictreatment.activity;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.appdata.StaticData;
import com.applicationslab.ayurvedictreatment.datamodel.HerbalPlantsData;

import java.util.ArrayList;

/**
 * Created by user on 6/8/2016.
 */
public class SuggestedDetailsActivity extends AppCompatActivity {

    LinearLayout linearContainer;

    String title = "";
    ArrayList<Integer> plantPositions = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_suggested_details);
        initData();
        initView();
        setAllContentData();
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
        linearContainer = (LinearLayout) findViewById(R.id.linearContainer);
    }

    private void setAllContentData() {
        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        ArrayList<HerbalPlantsData> herbalPlantsData = StaticData.getHerbalPlantsDatas();
        int size = plantPositions.size();

        TextView[] txtNames = new TextView[size];
        TextView[] txtScientificLabels = new TextView[size];
        TextView[] txtDescriptionLabels = new TextView[size];
        TextView[] txtUsesLabels = new TextView[size];
        TextView[] txtScientificNames = new TextView[size];
        TextView[] txtDescriptions = new TextView[size];
        TextView[] txtUsess = new TextView[size];
        ImageView[] imgViewHerbalPlants = new ImageView[size];
        View[] views = new View[size];
        LayoutInflater inflater = LayoutInflater.from(this);

        for (int i = 0; i < size; i++) {
            views[i] = inflater.inflate(R.layout.row_plant_details, null);
            txtNames[i] = (TextView) views[i].findViewById(R.id.txtName);
            txtScientificLabels[i] = (TextView) views[i].findViewById(R.id.txtScientificLabel);
            txtDescriptionLabels[i] = (TextView) views[i].findViewById(R.id.txtDescriptionLabel);
            txtUsesLabels[i] = (TextView) views[i].findViewById(R.id.txtUsesLabel);
            txtScientificNames[i] = (TextView) views[i].findViewById(R.id.txtScientificName);
            txtDescriptions[i] = (TextView) views[i].findViewById(R.id.txtDescription);
            txtUsess[i] = (TextView) views[i].findViewById(R.id.txtUses);
            imgViewHerbalPlants[i] = (ImageView) views[i].findViewById(R.id.imgViewHerbalPlant);

            txtNames[i].setTypeface(tfRegular, Typeface.BOLD);
            txtScientificNames[i].setTypeface(tfRegular, Typeface.ITALIC);
            txtDescriptions[i].setTypeface(tfRegular);
            txtUsess[i].setTypeface(tfRegular);
            txtScientificLabels[i].setTypeface(tfRegular, Typeface.BOLD);
            txtDescriptionLabels[i].setTypeface(tfRegular, Typeface.BOLD);
            txtUsesLabels[i].setTypeface(tfRegular, Typeface.BOLD);

            int position = plantPositions.get(i);
            txtNames[i].setText(herbalPlantsData.get(position).getName());
            txtDescriptions[i].setText(herbalPlantsData.get(position).getDetails());
            txtUsess[i].setText(herbalPlantsData.get(position).getUses());
            imgViewHerbalPlants[i].setImageResource(herbalPlantsData.get(position).getImageId());
            if(!"".equalsIgnoreCase(herbalPlantsData.get(position).getScientificName())) {
                txtScientificNames[i].setText(herbalPlantsData.get(position).getScientificName());
            } else {
                txtScientificLabels[i].setVisibility(View.GONE);
                txtScientificNames[i].setVisibility(View.GONE);
            }
            linearContainer.addView(views[i]);
        }

    }


}
