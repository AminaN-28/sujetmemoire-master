package com.example.shoppinlist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button reg_btn, log_btn;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





        reg_btn= findViewById(R.id.reg);
        log_btn = findViewById(R.id.log);



        reg_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reg_int= new Intent(MainActivity.this, Register.class);
                startActivity(reg_int);
            }
        });




        log_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  log_int= new Intent(MainActivity.this, Login.class);
                startActivity(log_int);
            }
        });
    }
}
