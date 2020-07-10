package com.example.shoppinglistmanager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<ListeArticle> lists;

    public ListAdapter (Context context, int layout,ArrayList<ListeArticle> lists){
        this.lists =lists;
        this.context = context;
        this.layout= layout;
    }
    @Override
    public int getCount() {
        return lists.size();
    }

    @Override
    public Object getItem(int position) {
        return lists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class ViewHolder{
        TextView listename,listeamount, listedate;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View Row =convertView;
        ListAdapter.ViewHolder holder = new ListAdapter.ViewHolder();

        if (Row  == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Row = inflater.inflate(layout, null);

            holder.listename= (TextView) Row.findViewById(R.id.title);
            holder.listeamount = (TextView) Row.findViewById(R.id.sous_titre);
            holder.listedate= (TextView) Row.findViewById(R.id.subtitle1);

            Row.setTag(holder);
        }
        else { holder = (ListAdapter.ViewHolder) Row.getTag();
        }
        ListeArticle l =lists.get(position);

        String amount1 =String.valueOf(l.getAmount());
        String date = String.valueOf(l.getDate());

        holder.listename.setText(l.getLibelé());
        holder.listeamount.setText(amount1);
        holder.listedate.setText(date);

        return Row;
    }
}
