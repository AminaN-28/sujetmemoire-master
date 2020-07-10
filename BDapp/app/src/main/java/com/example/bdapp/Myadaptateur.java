package com.example.bdapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;

public class Myadaptateur extends ArrayAdapter <Personne> {
    private Context context;
    private ArrayList <Personne> personne;


    public Myadaptateur (Context context, ArrayList<Personne> personne)
    {
        super(context,R.layout.personnerow, personne);
        this.context=context;
        this.personne= personne;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {//a chq fois quun elt disparait getView permet de le retrouver

        convertView= LayoutInflater.from(context).inflate(R.layout.personnerow,parent, false);
        Button ageBtn = convertView.findViewById(R.id.btn1);
        TextView nomtext = convertView.findViewById(R.id.textNom);
        TextView prenomText = convertView.findViewById(R.id.textPrenom);

        Personne p = personne.get(position);
        ageBtn.setText(p.getAge());
        nomtext.setText(p.getNom());
        prenomText.setText(p.getPrenom());

        return convertView;
    }
}
