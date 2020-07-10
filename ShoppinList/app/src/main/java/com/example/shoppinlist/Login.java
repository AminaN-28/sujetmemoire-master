package com.example.shoppinlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {



    Button login;
    EditText usern, mdp;

    SLmanager_DATABASE sLmanager_database;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);


// RECUPERATION DES PARAMETRES ENVOYES DE REGISTER.JAVA
       // final String firstname= getIntent().getStringExtra("Firstname");
        //final String surname = getIntent().getStringExtra("Surname");
        //final  String username = getIntent().getStringExtra("Username");
        //final String password = getIntent().getStringExtra("Password");


        usern =findViewById(R.id.edittext_username);
        mdp = findViewById(R.id.edittext_password);

        sLmanager_database = new SLmanager_DATABASE(this);

        login= findViewById(R.id.button_login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if ((usern.getText().toString().length() == 0) && (mdp.getText().toString().length() == 0))
                {
                    usern.setError("Saisissez votre nom d'utilisateur");
                    mdp.setError("Saisissez votre mot de passe");

                }
                else
                {
                    String user = usern.getText().toString().trim();
                    String pwd = mdp.getText().toString().trim();
                    Boolean res = sLmanager_database.checkUser(user, pwd);
                    if(res == true)
                    {
                        Toast.makeText(Login.this,"LOGIN SUCCESS! HI DEAR BUDDY", Toast.LENGTH_LONG).show();
                        Intent Log= new Intent(Login.this,ShoppingListShow.class);
                        startActivity(Log);
                    }
                    else
                    {
                        Toast.makeText(Login.this,"Login Error",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



    }


}
