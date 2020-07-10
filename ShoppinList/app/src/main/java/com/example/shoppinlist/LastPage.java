package com.example.shoppinlist;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LastPage extends AppCompatActivity {


    Button btnRotate;

    TextView textrotate;


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.disconnect, menu);

        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);


        //Lorsqu'on clique sur le menu et puis sur Log out on revient a la Page d'inscription si oui
        //Sinon lui demander s'il veut quitter oui rester.
        if (item.getItemId() == R.id.disconnect) {
            Intent FirstPage = new Intent(LastPage.this, MainActivity.class);//on peut aussi utiliser getApplicationContext()
            //a la place de Home_SL.this
            startActivity(FirstPage);
            return true;
        }
        return false;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_last_page);



        btnRotate = findViewById(R.id.Rotatebtn);
        textrotate = findViewById(R.id.text_rotate);
        final Animation animBounce = (Animation) AnimationUtils.loadAnimation(getApplicationContext(), R.anim.rotate);
        btnRotate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textrotate.startAnimation(animBounce);
                Toast.makeText(getApplicationContext(), "It was Just a JOke My Dear. You can't log out now.", Toast.LENGTH_LONG).show();

            }
        });
    }
}
