package com.example.shoppinlist;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ArticleAdapter extends BaseAdapter {
    private Context context;
    private  int layout;
    private ArrayList<ArticleClass> articlelists;

    public ArticleAdapter (Context context, int layout,ArrayList<ArticleClass> articlelists){
        this.articlelists =articlelists;
        this.context = context;
        this.layout= layout;
    }

    @Override
    public int getCount() {
        return articlelists.size();
    }

    @Override
    public Object getItem(int position) {
        return articlelists.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private class ViewHolder{
        ImageView imageView;
        TextView artcName, artcPrice, artcQte;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

         View Row =convertView;
        ViewHolder holder = new ViewHolder();
        if (Row  == null)
        {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            Row = inflater.inflate(layout, null);

            holder.artcName= (TextView) Row.findViewById(R.id.articlename);
            holder.artcPrice = (TextView) Row.findViewById(R.id.price_recup);
            holder.artcQte = (TextView) Row.findViewById(R.id.qte_recup);
            holder.imageView =(ImageView) Row.findViewById(R.id.Img_recup);
            Row.setTag(holder);

        }
        else {
            holder = (ViewHolder) Row.getTag();
        }

        ArticleClass A_C = articlelists.get(position);

        String qte1 =String.valueOf(A_C.getQte());
        String amnt1 = String.valueOf(A_C.getPrice());

        holder.artcName.setText(A_C.getNameA());
        holder.artcQte.setText(qte1);
        holder.artcPrice.setText(amnt1);

        byte [] artIMG =A_C.getImg();
        Bitmap bitmap = BitmapFactory.decodeByteArray(artIMG,0,artIMG.length);
        holder.imageView.setImageBitmap(bitmap);


        return Row;
    }
}
