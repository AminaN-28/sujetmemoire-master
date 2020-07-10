package com.example.shoppinlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;

import java.util.ArrayList;

public class ShowListArticle extends AppCompatActivity {
    Button addArt;
    GridView gridView;
    ArticleBD databaseArt;

    //static ArrayList<String> table = new ArrayList<>();
   ArrayList <ArticleClass> ArticleC = new ArrayList<>();

    ArticleAdapter adapter= null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.show_list_article);

        addArt = findViewById(R.id.addartt);
        gridView =findViewById(R.id.listview1);

        adapter =new ArticleAdapter(this,R.layout.article_added_row,ArticleC);
        gridView.setAdapter(adapter);

        //getting all data from database
        databaseArt = new ArticleBD(this,"shoppinglist.db",null,1);
        Cursor cursor = databaseArt.getData("SELECT * FROM SHOPPINGLIST");
        ArticleC.clear();
        while (cursor.moveToNext()){
            String name = cursor.getString(0);
            byte [] image =cursor.getBlob(1);
           int qte = cursor.getInt(2);
           int amount = cursor.getInt(3);

           ArticleC.add(new ArticleClass(name, qte, amount,image));
        }
        adapter.notifyDataSetChanged();


        addArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent addGo = new Intent(ShowListArticle.this,ShoppingListEditor.class);
                startActivity(addGo);
            }
        });
    }

}
