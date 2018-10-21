package com.devschema.sh4d0w.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends AppCompatActivity {

    private TextView tvName;
    private EditText etName;
    private Button btnStart;
    private RelativeLayout relativeLayoutMain;
    private String pName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById();

        getAnimations();//Load Animations

        //This filter also used in xml , just for info and learn i add both
        // android:digits="abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ"
        //set Name filter
        etName.setFilters(new InputFilter[]{
                new InputFilter() {
                    @Override
                    public CharSequence filter(CharSequence cs, int start,
                                               int end, Spanned spanned, int dStart, int dEnd) {
                        // TODO Auto-generated method stub
                        if (cs.equals("")) { // for backspace
                            return cs;
                        }
                        if (cs.toString().matches("[a-zA-Z ]+")) {
                            return cs;
                        }
                        return "";
                    }
                }
        });


        btnStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pName = etName.getText().toString();
                if (etName.getText().toString().trim().length() <= 0) {
                    Toast.makeText(HomeActivity.this, R.string.name_check_msg, Toast.LENGTH_SHORT).show();
                } else {
                    Intent intent = new Intent(getApplicationContext(), QuizActivity.class);
                    intent.putExtra("pName", pName);
                    startActivity(intent);

                }
            }
        });//btnStart

    }//onCreate

    private void findViewById() {
        tvName = (TextView) findViewById(R.id.tvName);
        etName = (EditText) findViewById(R.id.etName);
        btnStart = (Button) findViewById(R.id.btnStart);
        relativeLayoutMain = (RelativeLayout) findViewById(R.id.relativeLayoutMain);

    }//findViewById

    private void getAnimations() {
        //Animation Questions
        Animation animation;
        float animFrom = 0.0f, animTo = 1.0f;
        long animDuration = 1200;
        animation = new AlphaAnimation(animFrom, animTo);
        animation.setFillAfter(true);
        animation.setDuration(animDuration);
        relativeLayoutMain.startAnimation(animation);
        animation = AnimationUtils.loadAnimation(this, R.anim.move_down_slow);
        tvName.startAnimation(animation);

    }//getAnimations
}//HomeActivity
