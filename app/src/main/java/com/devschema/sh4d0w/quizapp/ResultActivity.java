package com.devschema.sh4d0w.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class ResultActivity extends AppCompatActivity {

    private String PersonName;
    private String TotalMark, q1Marks, q2Marks, q3Marks, q4Marks, q5Marks;

    private TextView tvq1, tvq2, tvq3, tvq4, tvq5 , tvTotal, tvPName, tvResultName;
    private double total = 0;

    private LinearLayout llResults,llResultsTotal;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        Log.d("TAG", "ResultActivity");

        // This code for screen rotation
        if(savedInstanceState!=null){
            //get PersonName
            PersonName = savedInstanceState.getString("pName");
            q1Marks=savedInstanceState.getString("q1Marks");
            q2Marks=savedInstanceState.getString("q2Marks");
            q3Marks=savedInstanceState.getString("q3Marks");
            q4Marks=savedInstanceState.getString("q4Marks");
            q5Marks=savedInstanceState.getString("q5Marks");

        }//  if(savedInstanceState!=null)

        //This code to get Data passed from previous Activity
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            PersonName = getIntent().getStringExtra("pName");
            q1Marks = getIntent().getStringExtra("q1Marks");
            q2Marks = getIntent().getStringExtra("q2Marks");
            q3Marks = getIntent().getStringExtra("q3Marks");
            q4Marks = getIntent().getStringExtra("q4Marks");
            q5Marks = getIntent().getStringExtra("q5Marks");

            TotalMark = getIntent().getStringExtra("TotalMark");
        }
        Log.d("TAG", "P Name ---------------------" + bundle.getString("pName"));

        findViewById();

        tvPName.setText("Done ! " + PersonName);
        tvq1.setText(q1Marks);
        tvq2.setText(q2Marks);
        tvq3.setText(q3Marks);
        tvq4.setText(q4Marks);
        tvq5.setText(q5Marks);

        tvTotal.setText(getResources().getString(R.string.total_mark) +"="+TotalMark + "    ");

         try {
           total = Double.valueOf(TotalMark);
        } catch (Exception e) {
            e.printStackTrace();
        }

        String ResultName;
        if (total == 10)
            ResultName = getResources().getString(R.string.excellent);
        else if (total > 7 && total < 10)
            ResultName = getResources().getString(R.string.vgood);
        else if (total > 5 && total < 7)
            ResultName = getResources().getString(R.string.good);
        else
            ResultName =getResources().getString(R.string.faild);
        tvResultName.setText(ResultName);

        getAnimations(); //Load Animations

    }//onCreate

    private void findViewById() {
        tvq1 = (TextView) findViewById(R.id.tvq1);
        tvq2 = (TextView) findViewById(R.id.tvq2);
        tvq3 = (TextView) findViewById(R.id.tvq3);
        tvq4 = (TextView) findViewById(R.id.tvq4);
        tvq5 = (TextView) findViewById(R.id.tvq5);

        tvTotal = (TextView) findViewById(R.id.tvTotal);
        tvPName = (TextView) findViewById(R.id.tvPName);
        tvResultName = (TextView) findViewById(R.id.tvResultName);

        llResults = (LinearLayout) findViewById(R.id.llResults);
        llResultsTotal = (LinearLayout) findViewById(R.id.llResultsTotal);

    }//findViewById

    private void getAnimations(){
        //Animation Questions
        Animation animation,animation1;
        float animFrom =0.0f,animTo=1.0f;
        long animDuration=1200;
        animation = new AlphaAnimation(animFrom, animTo);
        animation1 = new AlphaAnimation(animFrom, animTo);
        animation.setFillAfter(true);
        animation.setDuration(animDuration);
        llResults.startAnimation(animation);
        llResultsTotal.startAnimation(animation1);
        animation = AnimationUtils.loadAnimation(this, R.anim.move_down_slow);
        animation1 = AnimationUtils.loadAnimation(this, R.anim.zoom_in);

    }//getAnimations

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("pName",PersonName);
        outState.putString("q1Marks",q1Marks);
        outState.putString("q2Marks",q2Marks);
        outState.putString("q3Marks",q3Marks);
        outState.putString("q4Marks",q4Marks);
        outState.putString("q5Marks",q5Marks);

        outState.putString("TotalMark",TotalMark);

    }//onSaveInstanceState

}//ResultActivity
