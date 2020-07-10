package com.example.myquestionstab;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    private TextView Question1,score,progressScore;
  /* private int Tab[]=new int []{R.string.Q1, R.string.Q2, R.string.Q3, R.string.Q4,R.string.Q5,R.string.Q6,
           R.string.Q7, R.string.Q8,R.string.Q9,R.string.Q10, R.string.Q11, R.string.Q12};
    private TextView Q1,Q2,Q3,Q4,Q5,Q6,Q7,Q8,Q9,Q10,Q11,Q12;*/
   private Button trUebtn;
 private   Button falsebtn;
   ProgressBar progressBar;
   int indice;
   int scoreUser;
   int increaseProgressBar;
    private qUestionApp[] Quest1= new qUestionApp[]{new qUestionApp(R.string.Q1, true),
            new qUestionApp(R.string.Q2, true) ,new qUestionApp(R.string.Q3, true),
            new qUestionApp(R.string.Q4, false) ,new qUestionApp(R.string.Q5, false),
            new qUestionApp(R.string.Q6, true) ,new qUestionApp(R.string.Q7, false),
            new qUestionApp(R.string.Q8, false) ,new qUestionApp(R.string.Q9, true),
            new qUestionApp(R.string.Q10, true) ,new qUestionApp(R.string.Q11, false),
            new qUestionApp(R.string.Q12, false) };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        Log.d("Quizz6","on create lunched");

        increaseProgressBar= 100/Quest1.length;
        score=findViewById(R.id.score);
        Question1= findViewById(R.id.Q1);
        progressScore=findViewById(R.id.progressScore);
        progressBar=findViewById(R.id.progressBar);
        trUebtn=findViewById(R.id.trUebtn);
        falsebtn=findViewById(R.id.falsebtn);
        if (savedInstanceState!=null)
        {
            indice=savedInstanceState.getInt("indice", 0);
            scoreUser=savedInstanceState.getInt("score", 0);
            score.setText("Score:" +scoreUser);
            Question1.setText(Quest1[indice].getQuestion());
        }
        trUebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Quest1[indice].isAnswer()==true){
                    scoreUser++;
                    score.setText("Score : " +scoreUser);
                }
                indice=indice+1;
                Question1.setText(Quest1[indice].getQuestion());
                progressBar.incrementProgressBy(increaseProgressBar);
            }
        });

        falsebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Quest1[indice].isAnswer()==false){
                    scoreUser++;
                    score.setText("Score :" +scoreUser);
                }

                indice=indice+1;
                Question1.setText( Quest1[indice].getQuestion());
                progressBar.incrementProgressBy(increaseProgressBar);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Quizz6","on start lunched");

    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("Quizz6","on resume lunched");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("indice",indice);
        outState.putInt("score",scoreUser);
    }
}
