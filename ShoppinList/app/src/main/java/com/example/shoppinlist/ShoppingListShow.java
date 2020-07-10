package com.example.shoppinlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class ShoppingListShow extends AppCompatActivity {



    ListView liste;
    ListToShopBD articleBD;

    String namebdd;


    Button addbtn;

    static ArrayList<String> table = new ArrayList<>();
    ArrayList <ListeArticle> listeA = new ArrayList<>();

    ListeAdapter adp;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_note_menu, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);

        if (item.getItemId() == R.id.add_note) {

            Intent listShop_intent = new Intent(ShoppingListShow.this, List_Editor.class);//on peut aussi utiliser getApplicationContext()
            //a la place de ShoppingListShow.this
            startActivity(listShop_intent);

            return true;

        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shopping_list_show);
        articleBD = new ListToShopBD(this);
        liste= findViewById(R.id.shopListView);
        getValues();
        SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                ("com.example.shoppinlist", Context.MODE_PRIVATE);
        HashSet<String> set = (HashSet<String>) sharedPreferences.getStringSet("table", null);

        if (set == null) {
            table.add("Add List");

        } else {
            table = new ArrayList(set);

        }
        table.add("Example list");


        addbtn = findViewById(R.id.addbtn);
       addbtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent return_int = new Intent(ShoppingListShow.this, List_Editor.class);
               startActivity(return_int);

           }
       });



       //Au long clic d'une liste pour la supprimer
        liste.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, final int position, long id) {
                final int itemToDelete = position;

                new AlertDialog.Builder(ShoppingListShow.this)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setTitle("Are you sure?")
                        .setMessage("Do you want to delete this note?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                listeA.remove(itemToDelete);
                               // delete();
                                adp.notifyDataSetChanged();
                                SharedPreferences sharedPreferences = getApplicationContext().getSharedPreferences
                                        ("com.example.shoppinlist", Context.MODE_PRIVATE);
                                    HashSet<String> set = new HashSet<String>(ShoppingListShow.table);
                                sharedPreferences.edit().putStringSet("table", set).apply();
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();

                return true;
            }
        });


        //Au simple clic

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent goToArticle = new Intent(ShoppingListShow.this, ShowListArticle.class);
                startActivity(goToArticle);
            }
        });

    }

    public void getValues(){
        table.clear();
        SQLiteDatabase db = articleBD.getReadableDatabase();
        Cursor cursor = db.query("listedetails",new String []{"listename", "listeamount", "listedate"},
                null,null,null,null,null,null);
        while (cursor.moveToNext()){

            int indicelistname = cursor.getColumnIndex("listename");
            int indicelistamount = cursor.getColumnIndex("listeamount");
            int indicelistdate= cursor.getColumnIndex("listedate");


            String name = cursor.getString(indicelistname);
            String amount = cursor.getString(indicelistamount);
            String date = cursor.getString(indicelistdate);


            table.add(name);
            table.add(amount);
            table.add(date);

            int amount1 = Integer.valueOf(amount);
            ListeArticle l = new ListeArticle(name, amount1, date);

            listeA.add(l);

        }
        adp = new ListeAdapter(ShoppingListShow.this, listeA);
        liste.setAdapter(adp);

    }

    public void delete()
    {

        //articleBD.deleteListByName(namebdd);
        //Toast.makeText(getApplicationContext(),"List Deleted", Toast.LENGTH_SHORT).show();
       int value = articleBD.deleteData(namebdd);
            if(value<0){
                Toast.makeText(this, "Failed", Toast.LENGTH_SHORT).show();
            }else{
                Toast.makeText(this, "Deleted", Toast.LENGTH_SHORT).show();
            }


    }

    public void adapteur(){

        Boolean isUpdated = articleBD.updateData(namebdd);
        if(isUpdated == true)
        {
            Toast.makeText(this, "Updated", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(this, "not Updated", Toast.LENGTH_SHORT).show();
        }
    }

}
