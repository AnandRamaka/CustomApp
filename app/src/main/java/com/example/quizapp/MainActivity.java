package com.example.quizapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Dictionary;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    TextView scoreBox;
    TextView questionBox;
    EditText responseText;
    Button submitButton;
    String[] questions;
    Map<String, String> qa;
    int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        scoreBox = findViewById(R.id.scoreBox);
        questionBox = findViewById(R.id.questionBox);
        responseText = findViewById(R.id.answers);
        qa = new HashMap<String, String>() {{
            put("1+1", "2");
            put("11-8", "3");
            put("2+3", "5");
            put("5*2", "10");
            put("20/5", "4");
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
