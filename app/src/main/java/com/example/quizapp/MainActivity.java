package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.SharedPreferences;

import com.google.gson.Gson;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView scoreBox, questionBox, highBox;
    ConstraintLayout CL;
    EditText responseText;
    Button submitButton;
    int highScore;
    Map<String, String> qa;
    int score = 0;
    public int counter;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Gson gson = new Gson();
        Person p = new Person("John");
        String json = gson.toJson(p);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0); // 0 - for private mode
        editor = pref.edit();
        highScore = pref.getInt("high", 0);
        scoreBox = findViewById(R.id.scoreBox);
        questionBox = findViewById(R.id.questionBox);
        highBox = findViewById(R.id.highscore);
        highBox.setText("High Score: " + highScore);
        responseText = findViewById(R.id.answers);
        CL = (ConstraintLayout)(findViewById(R.id.CL1));

        final TextView counttime=findViewById(R.id.counttime);
        new CountDownTimer(21000,1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                counttime.setText("Time left: " + String.valueOf(20-counter));
                counter++;
            }
            @Override
            public void onFinish() {
                counttime.setText("Finished");
                submitButton.setOnClickListener(null);
                highScore = Math.max(score, highScore);
                editor.putInt("high", highScore);
                editor.commit();
                CL.setBackgroundColor(Color.RED);
                highBox.setText("High Score: " + highScore);
            }
        }.start();
        qa = new HashMap<String, String>() {{
            put("1+1", "2");
            put("16/4", "4");
            put("5+9", "14");
            put("45/9", "5");
            put("11-8", "3");
            put("2+3", "5");
            put("12+19", "31");
            put("5*2", "10");
            put("3*7", "21");
            put("11*4", "44");
            put("20/5", "4");
            put("-4+1", "-3");
            put("4+7", "11");
            put("3*-12", "-36");
            put("6-7", "-1");
        }};
        submitButton = findViewById(R.id.submitButton);
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.i("score","Typed text: " + responseText.getText() + " Real answer: " + qa.get(questionBox.getText()));
                if( qa.get(questionBox.getText()).equals((""+responseText.getText()).trim() )) {
                    System.out.println("Correct Answer");
                    score++;
                }
                else
                    score--;
                scoreBox.setText("Score: " + score);
                responseText.setText("");
                Random random = new Random();
                int randomNumber = random.nextInt(qa.size());
                questionBox.setText( "" + qa.keySet().toArray()[randomNumber] );
            }
        });
    }
}
