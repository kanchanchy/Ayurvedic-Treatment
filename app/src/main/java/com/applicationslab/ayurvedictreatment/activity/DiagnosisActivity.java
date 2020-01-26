package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.adapter.SpinAdapter;
import com.applicationslab.ayurvedictreatment.datamodel.DiagnosisData;
import com.applicationslab.ayurvedictreatment.utility.PreferenceUtil;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;
import com.applicationslab.ayurvedictreatment.R;

import java.util.ArrayList;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

/**
 * Created by user on 6/6/2016.
 */
public class DiagnosisActivity extends AppCompatActivity implements View.OnClickListener{

    Button btnSubmit;
    TextView txtSymptom1;
    TextView txtSymptom2;
    TextView txtSymptom3;
    TextView txtSymptom4;
    Spinner spinnerSymptom1;
    Spinner spinnerSymptom2;
    Spinner spinnerSymptom3;
    Spinner spinnerSymptom4;

    Typeface tfRegular;
    SpinAdapter primaryAdapter;
    SpinAdapter secondaryAdapter;
    ArrayList<String> primaries = new ArrayList<>();
    ArrayList<String> secondaries = new ArrayList<>();
    ArrayList<DiagnosisData> symptoms = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_diagnosis);
        initView();
        setUIClickHandler();
        initData();
        setSpinnerAdapter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_logout, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            finish();
            return true;
        } else if(item.getItemId() == R.id.action_logout) {
            showLogoutDialog();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initView() {
        Toolbar toolBar=(Toolbar)findViewById(R.id.toolBar);
        setSupportActionBar(toolBar);
        getSupportActionBar().setTitle("Diagnosis");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);
        txtSymptom1 = (TextView) findViewById(R.id.txtSymptom1);
        txtSymptom2 = (TextView) findViewById(R.id.txtSymptom2);
        txtSymptom3 = (TextView) findViewById(R.id.txtSymptom3);
        txtSymptom4 = (TextView) findViewById(R.id.txtSymptom4);
        spinnerSymptom1 = (Spinner) findViewById(R.id.spinnerSymptom1);
        spinnerSymptom2 = (Spinner) findViewById(R.id.spinnerSymptom2);
        spinnerSymptom3 = (Spinner) findViewById(R.id.spinnerSymptom3);
        spinnerSymptom4 = (Spinner) findViewById(R.id.spinnerSymptom4);

        tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        btnSubmit.setTypeface(tfRegular, Typeface.BOLD);
        txtSymptom1.setTypeface(tfRegular);
        txtSymptom2.setTypeface(tfRegular);
        txtSymptom3.setTypeface(tfRegular);
        txtSymptom4.setTypeface(tfRegular);
    }


    private void setUIClickHandler() {
        btnSubmit.setOnClickListener(this);

        spinnerSymptom1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position == 0) {
                    disableSecondaries();
                } else {
                    enableSecondaries();
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }


    private void initData() {
        primaries = new ArrayList<>();
        symptoms = new ArrayList<>();

        DiagnosisData diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Appendiictis");
        diagnosisData.setPrimarySymptom("Lower abdomen pain");
        ArrayList<String> tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Abdomen pain in right side");
        tempSymptoms.add("Have vomiting problem");
        tempSymptoms.add("Range of fever 100-102");
        tempSymptoms.add("Acute pain in lower abdomen");
        tempSymptoms.add("The muscles of  the right side of the abdomen becomes tense");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Asthma");
        diagnosisData.setPrimarySymptom("Gasping of breath");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Have coughing problem");
        tempSymptoms.add("Have tightness of chest");
        tempSymptoms.add("Have vomiting problem");
        tempSymptoms.add("Have abdominal pain");
        tempSymptoms.add("Bronchial tubes in lungs constricted");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Diabetes");
        diagnosisData.setPrimarySymptom("Copious urination");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Urine colour is pale");
        tempSymptoms.add("Quantity of urine is increased");
        tempSymptoms.add("Feels thirsty");
        tempSymptoms.add("Decrease weight");
        tempSymptoms.add("Anaemia & constipation increases");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("High blood pressure");
        diagnosisData.setPrimarySymptom("Pain toward the back of the head & neck");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Blood pressure level is rise up to 120/70");
        tempSymptoms.add("Pain in heart region increased");
        tempSymptoms.add("Nervousness, tension and fatiguate increased");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Malaria");
        diagnosisData.setPrimarySymptom("High fever");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Have headache");
        tempSymptoms.add("Shivering pain in the limbs");
        tempSymptoms.add("Temperature comes down");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Mumps");
        diagnosisData.setPrimarySymptom("Swelling and pain");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Felt pain in one ear with neck & jaw");
        tempSymptoms.add("Have fever");
        tempSymptoms.add("Have vomiting problem");
        tempSymptoms.add("Have meningitis problem");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Jaundice");
        diagnosisData.setPrimarySymptom("Yellow coloration of the eyes");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Have weakness");
        tempSymptoms.add("Have fever & headache");
        tempSymptoms.add("Yellow color of skin & urin");
        tempSymptoms.add("have dull pain in liver region");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Tuberculosis");
        diagnosisData.setPrimarySymptom("Persistent cough & hoarseness");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Pain in the shoulder");
        tempSymptoms.add("Have indigestion");
        tempSymptoms.add("Have chest pain");
        tempSymptoms.add("Blood in sputum");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        diagnosisData = new DiagnosisData();
        diagnosisData.setDisease("Measles");
        diagnosisData.setPrimarySymptom("Rashes in skin");
        tempSymptoms = new ArrayList<>();
        tempSymptoms.add("Have fever");
        tempSymptoms.add("Watering in eyes");
        tempSymptoms.add("Dry cough");
        diagnosisData.setSecondarySymptoms(tempSymptoms);
        symptoms.add(diagnosisData);

        primaries.add("Select a symptom");
        for (int i = 0; i < symptoms.size(); i++) {
            primaries.add(symptoms.get(i).getPrimarySymptom());
        }
    }


    private void setSpinnerAdapter() {
        primaryAdapter = new SpinAdapter(this, primaries);
        spinnerSymptom1.setAdapter(primaryAdapter);
        disableSecondaries();
    }


    private void disableSecondaries() {
        secondaries = new ArrayList<>();
        secondaries.addAll(primaries);
        secondaries.set(0, "Disabled now");
        secondaryAdapter = new SpinAdapter(this, secondaries);
        spinnerSymptom2.setAdapter(secondaryAdapter);
        spinnerSymptom3.setAdapter(secondaryAdapter);
        spinnerSymptom4.setAdapter(secondaryAdapter);
        spinnerSymptom2.setEnabled(false);
        spinnerSymptom3.setEnabled(false);
        spinnerSymptom4.setEnabled(false);
    }

    private void enableSecondaries() {
        secondaries = new ArrayList<>();
        secondaries.add("Select a symptom");
        int position = spinnerSymptom1.getSelectedItemPosition() - 1;
        secondaries.addAll(symptoms.get(position).getSecondarySymptoms());
        secondaryAdapter = new SpinAdapter(this, secondaries);
        spinnerSymptom2.setAdapter(secondaryAdapter);
        spinnerSymptom3.setAdapter(secondaryAdapter);
        spinnerSymptom4.setAdapter(secondaryAdapter);
        spinnerSymptom2.setEnabled(true);
        spinnerSymptom3.setEnabled(true);
        spinnerSymptom4.setEnabled(true);
    }


    private void showLogoutDialog() {
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

        txtWarning.setTypeface(tfRegular);
        txtMessage.setTypeface(tfRegular);
        txtAccept.setTypeface(tfRegular, Typeface.BOLD);
        txtCancel.setTypeface(tfRegular, Typeface.BOLD);

        txtMessage.setText("Are you sure you want to logout?");
        txtAccept.setText("LOGOUT");
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
                makeLogout();
            }
        });
    }

    private void makeLogout() {
        PreferenceUtil preferenceUtil = new PreferenceUtil(this);
        preferenceUtil.clearPreference();
        Intent intent = new Intent(DiagnosisActivity.this, LoginActivity.class);
        intent.putExtra("target_job", "diagnosis");
        startActivity(intent);
        finish();
    }


    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.btnSubmit) {
            if(spinnerSymptom1.getSelectedItemPosition() == 0) {
                new CustomToast(this, "Please select some symptoms", "", false);
            } else {
                int count = 0;
                if(spinnerSymptom2.getSelectedItemPosition() != 0) {
                    count++;
                }
                if(spinnerSymptom3.getSelectedItemPosition() != 0 && spinnerSymptom3.getSelectedItemPosition() != spinnerSymptom2.getSelectedItemPosition()) {
                    count++;
                }
                if(spinnerSymptom4.getSelectedItemPosition() != 0 && spinnerSymptom4.getSelectedItemPosition() != spinnerSymptom2.getSelectedItemPosition() && spinnerSymptom4.getSelectedItemPosition() != spinnerSymptom3.getSelectedItemPosition()) {
                    count++;
                }
                if(count == 0) {
                    new CustomToast(this, "Selected symptoms are not adequate", "", false);
                } else {
                    String text = symptoms.get((spinnerSymptom1.getSelectedItemPosition()-1)).getDisease();
                    if(count == 1) {
                        text = "You may be affected by " + text;
                    } else {
                        text = "You are be affected by " + text;
                    }
                    Intent intent = new Intent(DiagnosisActivity.this, DiagnosisResultActivity.class);
                    intent.putExtra("disease", text);
                    startActivity(intent);
                }
            }
        }
    }


}
