package com.example.calculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView text;
    double valeur1;
    double valeur2;
    int T;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text=findViewById(R.id.Txt);
    }
    public void listenbtn(View V)
        {
            if (V.getId()==R.id.ac)
            {
                text.setText("0");
            }
            else if (V.getId()==R.id.pourc)
            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText("%");
            }
            else if (V.getId()==R.id.div)
            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText("/");
                T=1;
            }
            else if (V.getId()==R.id.sept)
            {
              concat("7");
            }
            else if (V.getId()==R.id.huit)
            {
                concat("8");
            }
            else if (V.getId()==R.id.neuf)
            {
                concat("9");
            }
            else if (V.getId()==R.id.mult)
            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText("X");
                T=2;
            }
            else if (V.getId()==R.id.quatre)
            {
                concat("4");
            }
            else if (V.getId()==R.id.cinq)
            {
                concat("5");
            }
            else if (V.getId()==R.id.six)
            {
                concat("6");
            }
            else if (V.getId()==R.id.moins)
            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText("-");
                T=0;
            }
            else if (V.getId()==R.id.un)
            {
                concat("1");
            }
            else if (V.getId()==R.id.deux)
            {
                concat("2");
            }
            else if (V.getId()==R.id.trois)
            {
                concat("3");
            }
            else if (V.getId()==R.id.plus)
            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText("+");
                T=3;
            }
            else if (V.getId()==R.id.plusmoins)

            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText("-");
            }
            else if (V.getId()==R.id.zero)
            {
                concat("0");
            }
            else if (V.getId()==R.id.virgule)
            {
                valeur1=Double.parseDouble(text.getText().toString());
                text.setText(",");
            }
            else
            {
                valeur2=Double.parseDouble(text.getText().toString());
                if(T==3)
                    text.setText(String.valueOf((valeur1+valeur2)));
                else if(T==0)
                    text.setText(String.valueOf((valeur1-valeur2)));
                else if(T==2)
                    text.setText(String.valueOf((valeur1*valeur2)));

                else
                    text.setText(String.valueOf((valeur1/valeur2)));
            }
        }
        public void concat(String number)
        {
            String s=text.getText().toString();
            if (s.equals("0"))
            {
                text.setText(number);
            }
            else if (s.equals("+"))
            text.setText(number);
            else if (s.equals("-"))
            text.setText(number);
            else if (s.equals("X"))
            text.setText(number);
            else if (s.equals("/"))
            text.setText(number);
            else
            text.setText(s+number);
         }
}

