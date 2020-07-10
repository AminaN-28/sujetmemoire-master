package com.example.mymisha;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    Button Press;
    EditText message;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Press=findViewById(R.id.PrssBtn);
        message=findViewById(R.id.texto);
        Press.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userMessage=message.getText().toString();
                /*Intent intent = new Intent(MainActivity.this, ActivityA.class);//intent implicite
                intent.putExtra("Message",userMessage);//dire ce qu'on veut donner en argument a
                startActivity(intent);*/
                /**iNTENT IMPLICITE**/
               /*Intent intent =new Intent(Intent.ACTION_SEND);//action que l'on veut faire
                intent.setType("text/plain");//dire cest quel type de mEssage
                intent.putExtra(Intent.EXTRA_TEXT,userMessage);
                startActivity(intent);*/
               Intent intent =new Intent(Intent.ACTION_SENDTO);
                intent.setPackage("com.whatsapp");//ouvre directement sans demander Qd on l"enleve on envoie un message directement par carte sim
                intent.putExtra("sms_body:",userMessage);
                intent.setData(Uri.parse("smsto: +221771876021")); //mettre l'indicatif tjrs
                //intent.putExtra(Intent.EXTRA_TEXT,userMessage);
                startActivity(intent);

            }
        });
    }
}
