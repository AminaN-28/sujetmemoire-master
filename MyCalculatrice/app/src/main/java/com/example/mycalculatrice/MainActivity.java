package com.example.mycalculatrice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView Calcul;
    double valeur;
    double valeur2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Calcul=findViewById(R.id.calcul);
    }

    public void ListenButton(View view)
    {
        if (view.getId() == R.id.C_btn)
        {
            concat("c");
        }
        else if (view.getId() == R.id.parent_btn
        ) {
            concat("()");
        }
        else if (view.getId() == R.id.percent_btn)
        {
            concat("%");
        }
        else if (view.getId() == R.id.div_btn)
        {
            concat("/");
        }
        else if (view.getId() == R.id.seven_btn)
        {
            concat("7");
        }
        else if (view.getId() == R.id.eight_btn)
        {
            concat("8");
        }
        else if (view.getId() == R.id.nine_btn)
        {
            concat("9");
        }
        else if (view.getId() == R.id.mult_btn)
        {
            concat("*");
        }
        else if (view.getId() == R.id.for_btn)
        {
            concat("4");
        }
        else if (view.getId() == R.id.five_Btn)
        {
            concat("5");
        }
        else if (view.getId() == R.id.six_Btn)
        {
            Calcul.setText("6");
        }
        else if (view.getId() == R.id.Sous_Btn) {
            valeur = Double.parseDouble(Calcul.getText().toString());
            Calcul.setText("-");

        } else if (view.getId() == R.id.one_btn) {
            concat("1");

        } else if (view.getId() == R.id.two_Btn) {
            concat("2");
        } else if (view.getId() == R.id.tree_Btn) {
            concat("3");
        } else if (view.getId() == R.id.Plus_Btn) {
            valeur=Double.parseDouble(Calcul.getText().toString());
            Calcul.setText("+"); //ou ecrire concat("+")

        } else if (view.getId() == R.id.PlusSous_btn) {
            concat("+/-");
        } else if (view.getId() == R.id.zero_btn) {
            concat("0");
        } else if (view.getId() == R.id.vIrgule_btn) {
            concat(",");
        } else if (view.getId() == R.id.equals_btn) {
            valeur2= Double.parseDouble(Calcul.getText().toString());
            Calcul.setText(String.valueOf(valeur+valeur2)); //convertir le string en double
            Calcul.setText(String.valueOf(valeur-valeur2));
            Calcul.setText(String.valueOf(valeur*valeur2));
            Calcul.setText(String.valueOf(valeur/valeur2));
        }

    }

    public void concat(String number) {
        String s = Calcul.getText().toString();
        Calcul.setText(s + number);
        if (s.equals("0")) {
            Log.d("Calcul", s);
            Calcul.setText(number);
        }
        else if (s.equals("+"))
        {
            Log.d("Calcul", s);
            Calcul.setText(number);
        }
        else if (s.equals("="))
        {
            Log.d("Calcul", s);
            Calcul.setText(number);
        }
        else if (s.equals("-"))
        {
            Log.d("Calcul", s);
            Calcul.setText(number);
        }
        else
            {
            Calcul.setText(s+number);
        }
        }

   /* public enum Operation {
        Sous,
        Div,
        Mult;
    }
    public void calculer ()
    {
        if (!isOps){
            switch (op){
                case Sous: Op1=Op1-Op2; break;
                case Div: Op1=Op1/Op2;break;
                case Mult:Op1=Op1*Op2;break;
                default:return;
            }

        }
    }*/


}
