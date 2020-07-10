package com.example.bdapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.sip.SipSession;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    ListView liste;
    SQLiteHelper helper;
    ArrayAdapter  <String> adapter;// il se charge de mettre les datas dans notre listeview
    ArrayList <String>table = new ArrayList<>();//pour mettre tous les elements recuperes dedans
    ArrayList <String> tab= new ArrayList<>();
    ArrayList <Personne> listePersonne = new ArrayList<>();


    Myadaptateur adp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        helper=new SQLiteHelper(this);
        liste= findViewById(R.id.NomListeView);
        getValues();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) //pour mettre au niveau de l'ecran
    {
        getMenuInflater().inflate(R.menu.menuapp, menu);
        return super.onCreateOptionsMenu(menu);
    }
//Alertdialog pop-up qui s'affiche
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item)// pour gerer les interactions: on utilise la methode onOptionsItemSelected
    {
        //avec les if on saura ou il a clique
        if (R.id.addid==item.getItemId())
        {
            //Toast.makeText(this,"Addbtn" ,Toast.LENGTH_LONG).show();
            final EditText editnom =new EditText(this);// EditText permermet de saisir son nom
            editnom.setHint("Nom");

            final EditText editprenom =new EditText(this);
            editprenom.setHint("Prenom");

            final EditText editAge= new EditText(this);
            editAge.setHint("Age");

            LinearLayout layout = new LinearLayout(this);//pour mettre tous les views dedans

            layout.setOrientation(LinearLayout.VERTICAL);//pour changer l'orientation par defaut de linearlayout

            layout.addView(editnom);

            layout.addView(editprenom);

            layout.addView(editAge);

            AlertDialog alert = new AlertDialog.Builder((this))
                    .setTitle("INFO")
                    .setMessage("Insert Your Name")
                    .setView(layout)

                    .setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which)
                        {
                            String nom =editnom.getText().toString();
                            String prenom= editprenom.getText().toString();
                            String age = editAge.getText().toString();


                            SQLiteDatabase db= helper.getWritableDatabase();//ecrire et lire les mise a jour
                            //content value permet d'inserer



                            ContentValues values = new ContentValues();

                            values.put("nom",nom);

                            values.put("prenom",prenom);

                            values.put("age",Integer.valueOf(age) );

                            long a = db.insert("Personne",null,values);//si le a retourne -1 l'insertion cest pas bien passé
                            //retourne le nombrre d'enregistrement dns la table
                            if (a==-1)
                            {
                                Toast.makeText(MainActivity.this ,"insert failed", Toast.LENGTH_LONG).show();
                            }
                            else
                            {
                                Toast.makeText(MainActivity.this ,"insert success", Toast.LENGTH_LONG).show();
                            }
                        }
                    }).setNegativeButton("Cancel", null).create();// pop-up créé
            alert.show();//pour afficher le pop-up
            getValues();
        }
        else
        {
            Toast.makeText(this, "deletebtn" ,Toast.LENGTH_LONG).show();
        }
        return super.onOptionsItemSelected(item); //il sert de lIstener a chq fois que l'utilisateur clique
        // sur un bouton cest cette methode qui est lancé.
    }
    public void getValues()
    {
        table.clear();
        SQLiteDatabase db = helper.getReadableDatabase();
        /*Cursor resultats= db.query("personne" , new String[]{"nom", "prenom", "age"}, null , null ,null , null, null);//declaration de la table et de ses colonnes

        while (resultats.moveToNext()) //pour recuperer chaque element pour chaque colonne
        {
            int indicenom=resultats.getColumnIndex("nom");
            String nom=resultats.getString(indicenom);

            Log.d("123456",nom);//pour identfier notre message
            table.add(nom);
        }*/
        // Avec Where
        Cursor resultats= db.query("personne" , new String[]{"nom", "prenom", "age"}, null ,null, null ,null , null, null);
        while (resultats.moveToNext()) //pour recuperer chaque element pour chaque colonne
        {
            int indicenom=resultats.getColumnIndex("nom");
            int indiceprenom =resultats.getColumnIndex("prenom");
            int indiceage =resultats.getColumnIndex("age");
            String nom=resultats.getString(indicenom);
            String prenom= resultats.getString(indiceprenom);
            String age= resultats.getString(indiceage);
          //  Log.d("123456",nom);//pour identfier notre message
           // Log.d("2803",prenom);
            int age1=Integer.valueOf(age);
            Personne p = new Personne(nom, prenom, age1);
            table.add(nom);
            listePersonne.add(p);

        }
        //adapter= new ArrayAdapter<>(MainActivity.this ,android.R.layout.simple_list_item_1,table); // definition de notre adaptateur pour le design de notre table
        //liste.setAdapter(adapter);
        adp= new Myadaptateur(MainActivity.this, listePersonne);
        liste.setAdapter(adp);

    }
}
