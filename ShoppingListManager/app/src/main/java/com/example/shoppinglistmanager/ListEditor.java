package com.example.shoppinglistmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class ListEditor extends AppCompatActivity {
    EditText liste_name,liste_amount,listedate;
    Button Startbtn;
    ListToShopBD article_base;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_editor);
        liste_name= findViewById(R.id.editlist);
        liste_amount =findViewById(R.id.amountlist);

        Startbtn= findViewById(R.id.startbtn);


        article_base = new ListToShopBD(this,"shoppinlist.db", null, 1);

        listedate= findViewById(R.id.datelist);
        Calendar c = Calendar.getInstance();
        SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss a");
        String formattedDate = df.format(c.getTime());
        listedate.setText(formattedDate);



        Startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((liste_name.getText().toString().length() == 0) && (liste_amount.getText().toString().length() == 0)){

                    liste_amount.setError("VEUILLEZ RENSEIGNER LE MONTANT LIMITE DE VOTRE LISTE");
                    liste_name.setError("VEUILLEZ RENSEIGNER LE NOM DE VOTRE");
                }
                else {
                    article_base.insertData(
                            liste_name.getText().toString().trim(),
                            liste_amount.getText().toString().trim(),
                            listedate.getText().toString().trim()
                    );
                    Toast.makeText(ListEditor.this,"Successfully Added", Toast.LENGTH_LONG).show();
                    Intent ListGo = new Intent(ListEditor.this, ShoppingListShow.class);
                    startActivity(ListGo);
                }
            }
        });
    }
}
                    /*BASE DE DONNEES LOCALE

                    SQLiteDatabase sl = article_base.getWritableDatabase();
                    String liste = liste_name.getText().toString();
                    String amount = liste_amount.getText().toString();
                    String listeD = listedate.getText().toString();
                    ContentValues values = new ContentValues();
                    values.put("listename", liste);
                    values.put("listeamount", amount);
                    values.put("listedate",listeD);
                    long a = sl.insert("listedetails", null, values);//si le a retourne -1 l'insertion cest pas bien passé
                    //retourne le nombrre d'enregistrement dns la table
                    if (a == -1) { Toast.makeText(ListEditor.this, "insert failed", Toast.LENGTH_LONG).show(); }
                    else { Toast.makeText(ListEditor.this, "insert success", Toast.LENGTH_LONG).show();
                        Intent ListGo = new Intent(ListEditor.this, ShoppingListShow.class);
                        startActivity(ListGo); }*/
