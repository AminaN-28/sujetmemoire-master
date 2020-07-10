package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private int Tab[]= {R.drawable.dice1, R.drawable.dice2, R.drawable.dice3,
            R.drawable.dice4, R.drawable.dice5, R.drawable.dice6};
    private ImageView dice1,dice2;
    private Button rollBtn;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dice1=findViewById(R.id.dice1);
        dice2=findViewById(R.id.dice2);
        rollBtn=findViewById(R.id.rollBtn);
        rollBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Toast.makeText(MainActivity.this, "Hello",Toast.LENGTH_LONG).show();
                Random rand = new Random();
                int indice1 =rand.nextInt(6);
                dice1.setImageResource(Tab[indice1]);
                int indice2 =rand.nextInt(6);
                dice2.setImageResource(Tab[indice2]);
            }
        });
    }
}
