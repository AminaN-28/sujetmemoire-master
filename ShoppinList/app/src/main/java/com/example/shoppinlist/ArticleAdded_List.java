package com.example.shoppinlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ArticleAdded_List extends AppCompatActivity {


    ListView added_liste;

    Button okbtn,editbtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_added__list);
        added_liste=findViewById(R.id.addedlist);

        editbtn = findViewById(R.id.edit_button);
        okbtn = findViewById(R.id.ok_button);


        editbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent return_int = new Intent(ArticleAdded_List.this, ShoppingListEditor.class);
                startActivity(return_int);


            }

        });

        okbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent lastPage = new Intent(ArticleAdded_List.this, ShoppingListShow.class);
                startActivity(lastPage);

            }
        });


    }


}
