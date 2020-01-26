package com.applicationslab.ayurvedictreatment.activity;

import android.app.ProgressDialog;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;

import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.applicationslab.ayurvedictreatment.R;
import com.applicationslab.ayurvedictreatment.adapter.FeedbackAdapter;
import com.applicationslab.ayurvedictreatment.datamodel.FeedbackData;
import com.applicationslab.ayurvedictreatment.utility.Urls;
import com.applicationslab.ayurvedictreatment.widget.CustomToast;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by user on 6/18/2016.
 */
public class ViewFeedbackActivity extends AppCompatActivity{

    LinearLayout linearFeedback;
    LinearLayout linearMessage;
    TextView txtMessage;
    TextView txtCount;
    RecyclerView recyclerViewFeedback;

    FeedbackAdapter adapter;
    ArrayList<FeedbackData> feedbackDatas = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_feedback);
        initView();
        initData();
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
        getSupportActionBar().setTitle("Feedbacks");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtMessage = (TextView) findViewById(R.id.txtMessage);
        txtCount = (TextView) findViewById(R.id.txtCount);
        linearMessage = (LinearLayout) findViewById(R.id.linearMessage);
        linearFeedback = (LinearLayout) findViewById(R.id.linearFeedback);
        recyclerViewFeedback = (RecyclerView) findViewById(R.id.recyclerViewFeedback);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerViewFeedback.setLayoutManager(layoutManager);

        Typeface tfRegular = Typeface.createFromAsset(getAssets(), "fonts/OpenSansRegular.ttf");
        txtCount.setTypeface(tfRegular, Typeface.BOLD);
        txtMessage.setTypeface(tfRegular);
    }

    private void setAdapter() {
        adapter = new FeedbackAdapter(this, feedbackDatas);
        recyclerViewFeedback.setAdapter(adapter);
        if(feedbackDatas.size() > 0) {
            String text = "";
            if(feedbackDatas.size() == 1) {
                text = "1 feedback found";
            } else {
                text = feedbackDatas.size() + " feedbacks found";
            }
            txtCount.setText(text);
            linearMessage.setVisibility(View.GONE);
            linearFeedback.setVisibility(View.VISIBLE);
        } else {
            linearFeedback.setVisibility(View.GONE);
            linearMessage.setVisibility(View.VISIBLE);
        }
    }

    private void initData() {
        callGettingFeedbackApi();
    }

    private void callGettingFeedbackApi() {
        final ProgressDialog mDialog = new ProgressDialog(this);
        mDialog.setMessage("Please wait...");
        mDialog.setCancelable(false);
        mDialog.show();

        feedbackDatas = new ArrayList<>();

        String url= Urls.URL_GET_FEEDBACK;
        StringRequest mRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    mDialog.dismiss();
                    JSONObject json=new JSONObject(response);
                    int success=json.getInt("success");
                    if(success==1)
                    {
                        JSONArray jArray = json.getJSONArray("feedbacks");
                        for (int i = 0; i < jArray.length(); i++) {
                            JSONObject jObj = jArray.getJSONObject(i);
                            FeedbackData feedbackData = new FeedbackData();
                            feedbackData.setName(jObj.getString("username"));
                            feedbackData.setDetails(jObj.getString("comment"));
                            feedbackDatas.add(feedbackData);
                        }
                    } else {
                        new CustomToast(ViewFeedbackActivity.this, "No feedback found", "", false);
                    }
                    setAdapter();
                }
                catch (Exception e)
                {
                    new CustomToast(ViewFeedbackActivity.this, "Something went wrong", "", false);
                    setAdapter();
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                mDialog.dismiss();
                new CustomToast(ViewFeedbackActivity.this, "Something went wrong", "", false);
                setAdapter();
            }
        });
        Volley.newRequestQueue(this).add(mRequest);
    }

}
