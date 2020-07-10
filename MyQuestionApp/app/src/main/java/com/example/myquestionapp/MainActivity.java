package com.example.myquestionapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView Question, score, ProgressScore;
    Button truebtn,returnbtn;
    Button falsebtn,helpbtn;
    ProgressBar progressBar;
    int indice,scoreUser,increaseProgressbar;
    QuestionTab T[]=new QuestionTab[]{new QuestionTab(R.id., true), new QuestionTab(R.id.)};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        truebtn=findViewById(R.id.vr);
        falsebtn=findViewById(R.id.fal);
        progressBar=findViewById(R.id.progressBar);
        returnbtn=findViewById(R.id.retour);
        score=findViewById(R.id.score);
        Question=findViewById(R.id.Quest);
        ProgressScore=findViewById(R.id.An);


    }
}
