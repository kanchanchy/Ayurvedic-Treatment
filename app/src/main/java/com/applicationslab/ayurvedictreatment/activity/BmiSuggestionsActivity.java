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
public class BmiSuggestionsActivity extends AppCompatActivity {

    TextView txtDo;
    TextView txtDoNot;
    TextView txtDoLabel;
    TextView txtDoNotLabel;

    String shouldDo = "";
    String shouldNotDo = "";
    int bmiCase = 2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_suggestions);
        initData();
        setBmiSuggestions();
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
        getSupportActionBar().setTitle("Diet Suggestions");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        txtDo = (TextView) findViewById(R.id.txtDo);
        txtDoNot = (TextView) findViewById(R.id.txtDoNot);
        txtDoLabel = (TextView) findViewById(R.id.txtDoLabel);
        txtDoNotLabel = (TextView) findViewById(R.id.txtDoNotLabel);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtDo.setTypeface(tfRegular);
        txtDoNot.setTypeface(tfRegular);
        txtDoLabel.setTypeface(tfRegular, Typeface.BOLD);
        txtDoNotLabel.setTypeface(tfRegular, Typeface.BOLD);
    }

    private void initData() {
        try {
            Intent intent = getIntent();
            bmiCase = intent.getExtras().getInt("bmi_case");
        } catch (Exception e) {
        }
    }

    private void setContentData() {
        txtDo.setText(shouldDo);
        txtDoNot.setText(shouldNotDo);
    }

    private void setBmiSuggestions(){
        switch (bmiCase) {
            case 1:
                shouldDo = ">> Eat more often\n\n" +
                        ">> tDrink Milk\n\n" +
                        ">> Try Weight gainer shakes\n\n" +
                        ">> Use Bigger Plates\n\n" +
                        ">> Add cream to your coffee\n\n" +
                        ">> Take Creatine\n\n" +
                        ">> Get Quality Sleep\n\n" +
                        ">> Eat your protein first and vegetables last";
                shouldNotDo = ">> Drink water before meals\n\n" +
                        ">> Smoke";
                break;
            case 2:
                shouldDo = ">> Eat whole grain cereals\n\n" +
                        ">> Include salad with meals\n\n" +
                        ">> Eat fruits in between main meals\n\n" +
                        ">> Include fresh Garlic and Ginger for gravy\n\n" +
                        ">> Drink at least 8 glass water\n\n" +
                        ">> Do regular exercise";
                shouldNotDo = ">> More oil for the food preparation\n\n" +
                        ">> Fast foods\n\n" +
                        ">> Sweets, pastries and backed foods\n\n" +
                        ">> Meat and meat products\n\n" +
                        ">> Alcohol and alcoholic beverages";
                break;
            case 3:
                shouldDo = ">> Eat regular meals\n\n" +
                        ">> Eat plenty of fruit and vegetables\n\n" +
                        ">> Drink a ton water\n\n" +
                        ">> Eat high-fiber foods\n\n" +
                        ">> Use a smaller plate\n\n" +
                        ">> Control emotional eating\n\n" +
                        ">> Cut down on sugar and refined crabs";
                shouldNotDo = ">> Don't skip breakfast\n\n" +
                        ">> Don't ban foods\n\n" +
                        ">> Don't stock junk food\n\n" +
                        ">> Don't Drink a ton of alcohol\n\n" +
                        ">> Don't Starve yourself\n\n" +
                        ">> Deprive yourself of indulgences";
                break;
            default:
                break;
        }
    }

}
