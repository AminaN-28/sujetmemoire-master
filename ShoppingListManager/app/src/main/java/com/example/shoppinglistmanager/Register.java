package com.example.shoppinglistmanager;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {
    EditText nom,prenom,userN,MotDP, CnfPassword;
    Button regist_btn;
    SLmanager_DATABASE sLmanager_database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        nom= findViewById(R.id.Name);
        prenom = findViewById(R.id.Name1);
        regist_btn=findViewById(R.id.registerBtn);
        userN=findViewById(R.id.edittext_username);
        MotDP=findViewById(R.id.edittext_password);
        CnfPassword= findViewById(R.id.cnf_password);
        sLmanager_database = new SLmanager_DATABASE(this);
        regist_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if ((nom.getText().toString().length() == 0) && (prenom.getText().toString().length() == 0)
                        && (MotDP.getText().toString().length() == 0) && (userN.getText().toString().length()==0))
                {

                    nom.setError("VEUILLEZ RENSEIGNER VOTRE NOM");
                    prenom.setError("VEUILLEZ RENSEIGNER VOTRE PRENOM");
                    userN.setError("VEUILLEZ SAISIR VOTRE NOM D'UTILISATEUR");
                    MotDP.setError("VEUILLEZ RENSEIGNER UN  MOT DE PASSE");

                }
                else {
                    String user = userN.getText().toString().trim();
                    String pwd = MotDP.getText().toString().trim();
                    String cnf_pwd = CnfPassword.getText().toString().trim();

                    if(pwd.equals(cnf_pwd)){
                        long val = sLmanager_database.addUser(user,pwd);
                        if(val > 0)
                        {
                            Toast.makeText(Register.this,"SIGN IN SUCCESS! WELCOME TO COMPANY_SHOPPING_MANAGEMENT ", Toast.LENGTH_LONG).show();
                            Intent moveToLogin = new Intent(Register.this,Login.class);
                            startActivity(moveToLogin);
                        }
                        else{
                            Toast.makeText(Register.this,"Registration Error",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{ Toast.makeText(Register.this,"Password is not matching",Toast.LENGTH_SHORT).show(); }
                }
            }
        });
    }
}

