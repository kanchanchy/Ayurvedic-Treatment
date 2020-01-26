package com.applicationslab.ayurvedictreatment.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.applicationslab.ayurvedictreatment.R;

/**
 * Created by user on 6/5/2016.
 */
public class SymptomListActivity extends AppCompatActivity implements View.OnClickListener{

    TextView txtOptional, txtAppendicites, txtAsthma, txtBronchitis, txtDiabetes, txtDysentery, txtHeatDisease, txtBloodPressure, txtJaundice, txtMalaria, txtMumps;
    RelativeLayout relativeAppendicites, relativeAsthma, relativeBronchitis, relativeDiabetes, relativeDysentery, relativeHeatDisease, relativeBloodPressure, relativeJaundice, relativeMalaria, relativeMumps;
    String title;
    String symptom;
    String treatment;
    String dose;
    String suggestion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptom_list);
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
        getSupportActionBar().setTitle("Symptom Checker");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtOptional = (TextView) findViewById(R.id.txtOptional);
        txtAppendicites = (TextView) findViewById(R.id.txtAppendicites);
        txtAsthma = (TextView) findViewById(R.id.txtAsthma);
        txtBronchitis = (TextView) findViewById(R.id.txtBronchitis);
        txtDiabetes = (TextView) findViewById(R.id.txtDiabetes);
        txtDysentery = (TextView) findViewById(R.id.txtDysentery);
        txtHeatDisease = (TextView) findViewById(R.id.txtHeartDisease);
        txtBloodPressure = (TextView) findViewById(R.id.txtBloodPressure);
        txtJaundice = (TextView) findViewById(R.id.txtJaundice);
        txtMalaria = (TextView) findViewById(R.id.txtMalaria);
        txtMumps = (TextView) findViewById(R.id.txtMumps);
        relativeAppendicites = (RelativeLayout) findViewById(R.id.relativeAppendicites);
        relativeAsthma = (RelativeLayout) findViewById(R.id.relativeAsthma);
        relativeBronchitis = (RelativeLayout) findViewById(R.id.relativeBronchitis);
        relativeDiabetes = (RelativeLayout) findViewById(R.id.relativeDiabates);
        relativeDysentery = (RelativeLayout) findViewById(R.id.relativeDysentry);
        relativeHeatDisease = (RelativeLayout) findViewById(R.id.relativeHeartDisease);
        relativeBloodPressure = (RelativeLayout) findViewById(R.id.relativeBloodPressure);
        relativeJaundice = (RelativeLayout) findViewById(R.id.relativeJaundice);
        relativeMalaria = (RelativeLayout) findViewById(R.id.relativeMalaria);
        relativeMumps = (RelativeLayout) findViewById(R.id.relativeMumps);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtOptional.setTypeface(tfRegular);
        txtAppendicites.setTypeface(tfRegular);
        txtAsthma.setTypeface(tfRegular);
        txtBronchitis.setTypeface(tfRegular);
        txtDiabetes.setTypeface(tfRegular);
        txtDysentery.setTypeface(tfRegular);
        txtHeatDisease.setTypeface(tfRegular);
        txtBloodPressure.setTypeface(tfRegular);
        txtJaundice.setTypeface(tfRegular);
        txtMalaria.setTypeface(tfRegular);
        txtMumps.setTypeface(tfRegular);
    }

    private void setUIClickHandler() {
        relativeAppendicites.setOnClickListener(this);
        relativeAsthma.setOnClickListener(this);
        relativeBronchitis.setOnClickListener(this);
        relativeDiabetes.setOnClickListener(this);
        relativeDysentery.setOnClickListener(this);
        relativeHeatDisease.setOnClickListener(this);
        relativeBloodPressure.setOnClickListener(this);
        relativeJaundice.setOnClickListener(this);
        relativeMalaria.setOnClickListener(this);
        relativeMumps.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        title = "";
        if(v.getId() == R.id.relativeAppendicites) {
            title = "Appendicitis";
        } else if(v.getId() == R.id.relativeAsthma) {
            title = "Asthma";
        } else if(v.getId() == R.id.relativeBronchitis) {
            title = "Bronchitis";
        } else if(v.getId() == R.id.relativeDiabates) {
            title = "Diabetes";
        } else if(v.getId() == R.id.relativeDysentry) {
            title = "Dysentery";
        } else if(v.getId() == R.id.relativeHeartDisease) {
            title = "Heart Disease";
        } else if(v.getId() == R.id.relativeBloodPressure) {
            title = "High Blood Pressure";
        } else if(v.getId() == R.id.relativeJaundice) {
            title = "Jaundice";
        } else if(v.getId() == R.id.relativeMalaria) {
            title = "Malaria";
        } else if(v.getId() == R.id.relativeMumps) {
            title = "Mumps";
        }

        if(!"".equalsIgnoreCase(title)) {
            initializeData();
            Intent intent = new Intent(SymptomListActivity.this, SymptomDetailsActivity.class);
            intent.putExtra("title", title);
            intent.putExtra("symptom", symptom);
            intent.putExtra("treatment", treatment);
            intent.putExtra("dose", dose);
            intent.putExtra("suggestion", suggestion);
            startActivity(intent);
        }
    }


    private void initializeData() {
        if(title.equalsIgnoreCase("Appendicitis")) {
            symptom = ">> Sudden pain in the centre of abdomen.\n\n" +
                    ">> Patient has a mild fever vary on 100-102 degree.\n\n" +
                    ">> Patient vomit several times.";
            treatment = "Shulrajlouha";
            dose = "1 tab or 2 tabs. Two times daily & socked water of Triphala.";
            suggestion = "Rest is of utmost importance in this disease.The patient should resort to fasting which is the only cure for this disease.Hot compresses may be placed over the painful area several times daily.An abdominal message is also beneficial.";
        } else if(title.equalsIgnoreCase("Asthma")) {
            symptom = ">> It appears to be gasping for breath.\n\n" +
                    ">> There may be coughing.\n\n" +
                    ">> Tightness in the chest.\n\n" +
                    ">> Profuse & sweating & vomiting.";
            treatment = "Kanakasab";
            dose = "12 ml(4 tsp) twice daily";
            suggestion = "The patient should be made to perspire through steam bath,hot foot bath.Asthametic should always eat slowly,chewing their food properly.Honey is considered highly beneficial in the treatment of asthma.The patient should avoid dust,exposure to cold,food to which is sensitive,mental worries & tension.";
        } else if(title.equalsIgnoreCase("Bronchitis")) {
            symptom = ">> The larynx,trachea and bronchial tubes are acutely inflamed.\n\n" +
                    ">> There is highly fever,some difficulty in breathing and deep.\n\n" +
                    ">> Chest cough.";
            treatment = "Basakarista";
            dose = "12 ml(4 tsp) twice daily";
            suggestion = "One of the most effective remedies for bronchitics is the use of turmeric powder.A teaspoonful of this powder should be administerd with glass of milk two or three times daily.Another effective remedy for this disease is mixture of dried ginger powder,pepper taken in equal quantities three times daily.";
        }
        else if(title.equalsIgnoreCase("Diabetes")) {
            symptom = ">> Copious urination and glucose in the urine.\n\n" +
                    ">> Patient feels thirsty,and put on weight.he feels drowsy,weakness.";
            treatment = "Jambadyaristy";
            dose = "12 ml(4 tsp) twice daily";
            suggestion = "Diet plays a vital role in such treatment.The patient should avoid overeating & take four or five meals a day.The bitter gourd is highly beneficial in the treatment.The patient should avoid tea,coffee.Exercise is most important in the treatment in diabetes.Hydrotherapy & colonic irrigation form a very important.";
        }
        else if(title.equalsIgnoreCase("Dysentery")) {
            symptom = ">> Stool remains putrid & may contain blood.\n\n" +
                    ">> Diarrhoea and constipation may alternate.\n\n" +
                    ">> The temperature may be rise 104-105.";
            treatment = "Brihatkutajabaleha";
            dose = "6 to 12g two times daily or according to physicion";
            suggestion = "Flesh foods of all kind should be avoided.tea,coffee,white sugar are avoided.The use of small pieces of onion mixed with curd & equal parts of tender leaves of the peepal tree,coriander leaves& sugar chewed slowly.";
        }
        else if(title.equalsIgnoreCase("Heart Disease")) {
            symptom = ">> Shortness of breath\n\n" +
                    ">> Chest pain or pain down either arm.palpitation,fainting,emotional instability.";
            treatment = "Arjunarista";
            dose = "12ml(4 tsp )twice daily";
            suggestion = "Fruits & vegetables in general are highly beneficial.Best food source are unrefined,raw, crude vegetable oils,seeds ,grain.One teaspoon raw onion juice 1st thing in the morning will be highly beneficial.";
        } else if(title.equalsIgnoreCase("High Blood Pressure")) {
            symptom = ">> Pain toward the back of the head & neck.\n\n" +
                    ">> Dizziness, aches & pain in the arm & shoulder,leg back.";
            treatment = "Mrityunjoy";
            dose = "12ml(4 tsp) in twice  daily";
            suggestion = "Person with high blood pressure should always follow a well balanced diet,exercise & rest.Vegetables are good for patient.Exercise plays an important role in curing hypertension.";
        } else if(title.equalsIgnoreCase("Jaundice")) {
            symptom = ">> Extreme weakness,headache,fever,lose of appetite,undue fatigue,severe constipation,nauses.\n\n" +
                    ">> Yellow coloration of the eyes,tongue,skin and urine.";
            treatment = "Partrangasav";
            dose = "12ml(4 tsp) in twice daily";
            suggestion = "All fats must be avoided for two week.Drinking with lot of water with lemon juice will protect the damaged liver cells.The juice of bitter is regared as an effective remedy for jaundice.";
        } else if(title.equalsIgnoreCase("Malaria")) {
            symptom = ">> High fever,which may come every day\n\n" +
                    ">> The fever is accompanied by chill,headache,shivering and pains in limbs.";
            treatment = "Amritarista";
            dose = "12 ml(4 tsp) in twice daily";
            suggestion = "The patient should avoid tea,coffee,fried food,sauces,white sugar,Avoid all alcoholic drinks.Lime & lemon are beneficial in the treatment of malarial fever.The best way to protect against is to adopt all measuring necessary preventive mosquito bite.The leaves of the holy basil are considered beneficial in the preventation of malaria.";
        } else if(title.equalsIgnoreCase("Mumps")) {
            symptom = ">> Swelling pain.The pain is 1st felt under one ear with stiffness of the neck & jaw.\n\n" +
                    ">> There is fever.\n" +
                    ">> Vommiting problem occurs.";
            treatment = "Baharernani";
            dose = "2 tsp two times daily with milk & sugar";
            suggestion = "The patient should be put in bed for several days until the temperature return to normal.The leaves of the peepal tree are another effective home remedy.";
        }
    }


}
