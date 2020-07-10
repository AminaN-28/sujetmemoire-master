package com.example.shoppinlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashSet;

public class Home_SL extends AppCompatActivity {


    //Button createbtn;
    static ArrayList<String> notes = new ArrayList<>();
    static  ArrayAdapter arrayAdapter;












    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home__sl);


        final String article = getIntent().getStringExtra("article");
        final String quantite=getIntent().getStringExtra("qte") ;
        final String img= getIntent().getStringExtra("img");
        final String amount = getIntent().getStringExtra("amount");



        ListView listView = findViewById(R.id.listView);
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences("com.example.shoppinlist", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("notes", null);

        if (set == null) {
            notes.add("Add note");

        } else {
            notes = new ArrayList(set);

        }
       // notes.add("Add note");
        notes.add("Example note");

        arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);

        listView.setAdapter(arrayAdapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //INTENT EXPLICITE

                Intent intent = new Intent(getApplicationContext(),ShoppingListShow.class);
                intent.putExtra("noteId", i);
                startActivity(intent);

            }
        });



        //Suppression d'une listview au long click
        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {

                final int itemToDelete = i;

                new AlertDialog.Builder(Home_SL.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                                notes.remove(itemToDelete);
                                arrayAdapter.notifyDataSetChanged();

                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                                        ("com.example.shoppinlist", Context.MODE_PRIVATE);
                                HashSet<String> set = new HashSet<String>(Home_SL.notes);
                                sharedPreferences.edit().putStringSet("notes", set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });




    }

}



/*createbtn=findViewById(R.id.createBtn);

        createbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent createInt =  new Intent(Home_SL.this,ShoppingListEditor.class);
                startActivity(createInt);
            }
        });*/



