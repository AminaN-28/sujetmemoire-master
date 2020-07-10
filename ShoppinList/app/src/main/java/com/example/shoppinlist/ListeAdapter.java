package com.example.shoppinlist;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import java.util.ArrayList;

public class ListeAdapter extends ArrayAdapter<ListeArticle> {

    private Context context;
    private ArrayList<ListeArticle> listeArticle;

    public ListeAdapter(Context context, ArrayList<ListeArticle> listeArticle) {
        super(context, R.layout.list__row, listeArticle);
        this.context=context;
        this.listeArticle = listeArticle;
    }



    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView= LayoutInflater.from(context).inflate(R.layout.list__row,parent, false);
        TextView libele = convertView.findViewById(R.id.title);
        TextView amount = convertView.findViewById(R.id.sous_titre);
        TextView date =convertView.findViewById(R.id.subtitle1);

        ListeArticle l = listeArticle.get(position);

        String amnt =String.valueOf(l.getAmount());

        libele.setText(l.getLibelé());
        amount.setText(amnt);
        date.setText(l.getDate());


        return convertView;
    }
}
