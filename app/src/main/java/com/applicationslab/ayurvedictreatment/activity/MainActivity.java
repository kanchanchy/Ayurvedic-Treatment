package com.applicationslab.ayurvedictreatment.activity;

import android.graphics.Typeface;
import android.os.Bundle;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.adapter.OptionsAdapter;
import com.applicationslab.ayurvedictreatment.datamodel.OptionsData;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerViewOptions;
    OptionsAdapter optionsAdapter;
    ArrayList<OptionsData> optionsData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initData();
        setAdapter();
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        LayoutInflater inflater = LayoutInflater.from(this);
        View view = inflater.inflate(R.layout.dialog_warning, null);
        builder.setView(view);
        final AlertDialog alertDialog = builder.create();
        alertDialog.show();

        TextView txtWarning = (TextView) view.findViewById(R.id.txtWarning);
        TextView txtMessage = (TextView) view.findViewById(R.id.txtMessage);
        TextView txtAccept = (TextView) view.findViewById(R.id.txtAccept);
        TextView txtCancel = (TextView) view.findViewById(R.id.txtCancel);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");

        txtWarning.setTypeface(tfRegular);
        txtMessage.setTypeface(tfRegular);
        txtAccept.setTypeface(tfRegular, Typeface.BOLD);
        txtCancel.setTypeface(tfRegular, Typeface.BOLD);

        txtMessage.setText("Are you sure you want to exit now?");
        txtAccept.setText("EXIT");
        txtCancel.setText("CANCEL");

        txtCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });

        txtAccept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
                finish();
            }
        });
    }

    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Ayurvedic Treatment");

        recyclerViewOptions = (RecyclerView) findViewById(R.id.recyclerViewOptions);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewOptions.setLayoutManager(layoutManager);
    }

    private void setAdapter() {
        optionsAdapter = new OptionsAdapter(this, optionsData);
        recyclerViewOptions.setAdapter(optionsAdapter);
    }

    private void initData() {
        optionsData = new ArrayList<>();
        OptionsData optionsRow = new OptionsData();
        optionsRow.setName("Symptom Checker");
        optionsRow.setDetails("Check symptoms of some common diseases ...");
        optionsRow.setImageId(R.drawable.symptom);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("Diagnosis");
        optionsRow.setDetails("Diagnose using observed symptoms ...");
        optionsRow.setImageId(R.drawable.diagnosis);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("Prescription Request");
        optionsRow.setDetails("Make a prescription request to doctor ...");
        optionsRow.setImageId(R.drawable.prescription);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("Herbal Plant");
        optionsRow.setDetails("Some herbal plants and deseases decsriptions ...");
        optionsRow.setImageId(R.drawable.herbal_plant);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("BMI Calculator");
        optionsRow.setDetails("Calculate BMI using weight and height ...");
        optionsRow.setImageId(R.drawable.bmi);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("Ayurvedic Hospitals");
        optionsRow.setDetails("Check the locations of nearest ayurvedic hospitals ...");
        optionsRow.setImageId(R.drawable.maps);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("Feedback");
        optionsRow.setDetails("Give a feedback about the application ...");
        optionsRow.setImageId(R.drawable.feedback);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("About AT App");
        optionsRow.setDetails("Get an idea of AT application ...");
        optionsRow.setImageId(R.drawable.about_app);
        optionsData.add(optionsRow);
        optionsRow = new OptionsData();
        optionsRow.setName("About Developer");
        optionsRow.setDetails("Get an idea of the developer of AT application ...");
        optionsRow.setImageId(R.drawable.about_developer);
        optionsData.add(optionsRow);
    }

}
