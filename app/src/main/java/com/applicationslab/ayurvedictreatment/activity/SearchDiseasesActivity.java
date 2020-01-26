package com.applicationslab.ayurvedictreatment.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.adapter.DiseaseAdapter;
import com.applicationslab.ayurvedictreatment.datamodel.DiseaseSearchData;

import java.util.ArrayList;

/**
 * Created by user on 6/6/2016.
 */
public class SearchDiseasesActivity extends AppCompatActivity {

    RecyclerView recyclerViewDiseases;
    DiseaseAdapter diseaseAdapter;
    ArrayList<DiseaseSearchData> diseaseSearchData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_diseases);
        initView();
        initData();
        setAdapter();
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
        getSupportActionBar().setTitle("Search by Diseases");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        recyclerViewDiseases = (RecyclerView) findViewById(R.id.recyclerViewDiseases);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewDiseases.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        diseaseAdapter = new DiseaseAdapter(this, diseaseSearchData);
        recyclerViewDiseases.setAdapter(diseaseAdapter);
    }

    private void initData() {
        diseaseSearchData = new ArrayList<>();
        DiseaseSearchData rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Acute Fever");
        ArrayList<Integer>positions = new ArrayList<>();
        positions.add(14);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Bronchatices");
        positions = new ArrayList<>();
        positions.add(14);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Asthma");
        positions = new ArrayList<>();
        positions.add(14);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Kidney Stone");
        positions = new ArrayList<>();
        positions.add(14);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Children Aliment");
        positions = new ArrayList<>();
        positions.add(14);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Skin Disease");
        positions = new ArrayList<>();
        positions.add(14);
        positions.add(1);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Reumatic arthritis");
        positions = new ArrayList<>();
        positions.add(8);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Dysmenorrhoea");
        positions = new ArrayList<>();
        positions.add(8);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Diabeties");
        positions = new ArrayList<>();
        positions.add(1);
        positions.add(2);
        positions.add(13);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Persistent fever");
        positions = new ArrayList<>();
        positions.add(2);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Heart disorder");
        positions = new ArrayList<>();
        positions.add(7);
        positions.add(12);
        positions.add(3);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Eczema");
        positions = new ArrayList<>();
        positions.add(0);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Jaundice");
        positions = new ArrayList<>();
        positions.add(0);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Gastric trouble");
        positions = new ArrayList<>();
        positions.add(11);
        positions.add(13);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Cholestrelol control");
        positions = new ArrayList<>();
        positions.add(13);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Constipation");
        positions = new ArrayList<>();
        positions.add(13);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Tuberculosis");
        positions = new ArrayList<>();
        positions.add(13);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Gangrene");
        positions = new ArrayList<>();
        positions.add(3);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
        rowData = new DiseaseSearchData();
        rowData.setDiseaseName("Fungal infection");
        positions = new ArrayList<>();
        positions.add(3);
        rowData.setPlantPositions(positions);
        diseaseSearchData.add(rowData);
    }

}
