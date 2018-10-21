package com.devschema.sh4d0w.quizapp;

import android.content.Intent;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;

public class QuizActivity extends AppCompatActivity implements View.OnClickListener {

    private String PersonName; // to store Person Name from First Activity

    private Button btnFinishQuiz;  // Submit Button
    private LinearLayout llMainAnim;

    private double TotalMark = 0, q1Marks = 0, q2Marks = 0, q3Marks = 0, q4Marks = 0, q4MarksA = 0, q4MarksB = 0, q5Marks = 0;

    private ArrayList<CheckBox> mChecks;
    private ArrayList<CheckBox> mSelectedChecks;
    private CheckBox ch1, ch2, ch3, ch4;  //ch2,ch3 Correct Answers
    private boolean isCh1Cheked,isCh2Cheked,isCh3Cheked,isCh4Cheked;
    private RadioButton rdq1a2, rdq2a4, rdq3a1;
    private EditText etq5Answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        findViewById(); // Get Views By Id Method

        mChecks = new ArrayList<CheckBox>();
        mSelectedChecks = new ArrayList<CheckBox>();

        if (savedInstanceState != null) {
            //get PersonName
            PersonName = savedInstanceState.getString("pName");
            q1Marks = savedInstanceState.getDouble("q1Marks");
            q2Marks = savedInstanceState.getDouble("q2Marks");
            q3Marks = savedInstanceState.getDouble("q3Marks");
            q4Marks = savedInstanceState.getDouble("q4Marks");
            q5Marks = savedInstanceState.getDouble("q5Marks");

            isCh1Cheked = savedInstanceState.getBoolean("isCh1Cheked");
            isCh2Cheked = savedInstanceState.getBoolean("isCh2Cheked");
            isCh3Cheked = savedInstanceState.getBoolean("isCh3Cheked");
            isCh4Cheked = savedInstanceState.getBoolean("isCh4Cheked");

            Log.d("TAG", "mSelectedChecks " + mSelectedChecks.size());

            Log.d("TAG", "\n onCreate isCh1Cheked " + isCh1Cheked);
            Log.d("TAG", "onCreate isCh2Cheked " + isCh2Cheked);
            Log.d("TAG", "onCreate isCh3Cheked " + isCh3Cheked);
            Log.d("TAG", "onCreate isCh4Cheked " + isCh4Cheked);


            //To be used for screen rotation
            if (isCh1Cheked){
                ch1.setChecked(true);
                mSelectedChecks.add(ch1);
            }
            if (isCh2Cheked){
                ch2.setChecked(true);
                mSelectedChecks.add(ch2);
            }
            if (isCh3Cheked){
                ch3.setChecked(true);
                mSelectedChecks.add(ch3);
            }
            if (isCh4Cheked){
                ch4.setChecked(true);
                mSelectedChecks.add(ch4);
            }


        }//  if(savedInstanceState!=null)

        getAnimations(); // Get Animations


        //Add to tracking list
        mChecks.add(ch1);
        mChecks.add(ch2);
        mChecks.add(ch3);
        mChecks.add(ch4);

        //Add Click listener
        for (CheckBox c : mChecks) {
            c.setOnClickListener(this);
        }

        PersonName = getIntent().getStringExtra("pName");

        btnFinishQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getCheckBoxAnswers(); // Get Results from CheckBox
                getRadioButtonAnswers(); // Get Results from RadioButtons
                getEditTextAnswer(); // Get Results from Question 6 EditText

                TotalMark = q1Marks + q2Marks + q3Marks + q4Marks + q5Marks;

                String msg = "----------------------------------------- \n"+
                        "q1Marks=" + q1Marks +
                        "\nq2Marks=" + q2Marks + "\nq3Marks=" + q3Marks + "\nq4Marks=" + q4Marks
                        + "\nq5Marks="+ q5Marks;

                Toast.makeText(QuizActivity.this, getResources().getString(R.string.total_mark) + "=" + TotalMark + "\n" + msg, Toast.LENGTH_LONG).show();

                Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                intent.putExtra("q1Marks", String.valueOf(q1Marks));
                Log.d("TAG", "putExtra " + q1Marks);

                intent.putExtra("q2Marks", String.valueOf(q2Marks));
                Log.d("TAG", "putExtra " + q2Marks);

                intent.putExtra("q3Marks", String.valueOf(q3Marks));
                Log.d("TAG", "putExtra " + q3Marks);

                intent.putExtra("q4Marks", String.valueOf(q4Marks));
                Log.d("TAG", "putExtra " + q4Marks);

                intent.putExtra("q5Marks", String.valueOf(q5Marks));
                Log.d("TAG", "putExtra " + q5Marks);

                intent.putExtra("TotalMark", String.valueOf(TotalMark));
                Log.d("TAG", "putExtra " + TotalMark);

                intent.putExtra("pName", String.valueOf(PersonName));
                Log.d("TAG", "putExtra " + PersonName);

                startActivity(intent);

            }
        });
    }//onCreate

    private void getRadioButtonAnswers() {
        if (rdq1a2.isChecked())
            q1Marks = 2;
        else
            q1Marks = 0;

        if (rdq2a4.isChecked())
            q2Marks = 2;
        else
            q2Marks = 0;

        if (rdq3a1.isChecked())
            q3Marks = 2;
        else
            q3Marks = 0;
    }//getRadioButtonAnswers

    //Get CheckBox Data
    private void getCheckBoxAnswers() {
        //2,3 are correct answers
        if (ch2.isChecked())
            q4MarksA = 1;
        else
            q4MarksA = 0;

        if (ch3.isChecked())
            q4MarksB = 1;
        else
            q4MarksB = 0;

        q4Marks = q4MarksA + q4MarksB;
    }//getCheckBoxAnswers

    private void getEditTextAnswer() {
        int val = 0;
        try {
            val = Integer.parseInt(etq5Answer.getText().toString().trim());
        } catch (Exception e) {
            val = 0;
        }
        if (val == 10)
            q5Marks = 2;
        else
            q5Marks = 0;

    }//getEditTextAnswer

    private void findViewById() {
        btnFinishQuiz = (Button) findViewById(R.id.btnFinishQuiz);
        llMainAnim = (LinearLayout) findViewById(R.id.llMainAnim);

        //Question #1
        rdq1a2 = (RadioButton) findViewById(R.id.rdq1a2);

        //Question #2
        rdq2a4 = (RadioButton) findViewById(R.id.rdq2a4);

        //Question #3
        rdq3a1 = (RadioButton) findViewById(R.id.rdq3a1);

        //Question #4
        ch1 = (CheckBox) findViewById(R.id.ch1);
        ch2 = (CheckBox) findViewById(R.id.ch2);
        ch3 = (CheckBox) findViewById(R.id.ch3);
        ch4 = (CheckBox) findViewById(R.id.ch4);


        //Question #5
        etq5Answer = (EditText) findViewById(R.id.etq6Answer);
    }//findViewById

    private void getAnimations() {
        //Animation Questions
        Animation animation;
        float animFrom = 0.0f, animTo = 1.0f;
        long animDuration = 1200;
        animation = new AlphaAnimation(animFrom, animTo);
        animation.setFillAfter(true);
        animation.setDuration(animDuration);
        llMainAnim.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.zoom_in);
    }//getAnimations

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("pName", PersonName);
        outState.putDouble("q1Marks", q1Marks);
        outState.putDouble("q2Marks", q2Marks);
        outState.putDouble("q3Marks", q3Marks);
        outState.putDouble("q4Marks", q4Marks);
        outState.putDouble("q5Marks", q5Marks);

        //To be used for screen rotation
        isCh1Cheked=ch1.isChecked();
        Log.d("TAG", "\n onSaveInstanceState isCh1Cheked " + isCh1Cheked);

        isCh2Cheked=ch2.isChecked();
        Log.d("TAG", "onSaveInstanceState isCh2Cheked " + isCh2Cheked);

        isCh3Cheked=ch3.isChecked();
        Log.d("TAG", "onSaveInstanceState isCh3Cheked " + isCh3Cheked);

        isCh4Cheked=ch4.isChecked();
        Log.d("TAG", "onSaveInstanceState isCh4Cheked " + isCh4Cheked);

        outState.putBoolean("isCh1Cheked", isCh1Cheked);
        outState.putBoolean("isCh2Cheked", isCh2Cheked);
        outState.putBoolean("isCh3Cheked", isCh3Cheked);
        outState.putBoolean("isCh4Cheked", isCh4Cheked);

    }//onSaveInstanceState


    @Override
    public void onClick(View v) {
        CheckBox c = (CheckBox) v;
        if (c.isChecked())
            if (mSelectedChecks.size() < 2) {
                mSelectedChecks.add(c);
            } else {
                c.setChecked(false);
                Toast.makeText(this, getResources().getString(R.string.checkbox_selection_msg), Toast.LENGTH_SHORT).show();
            }

        if (!c.isChecked())
            mSelectedChecks.remove(c);


    }

}//QuizActivity
