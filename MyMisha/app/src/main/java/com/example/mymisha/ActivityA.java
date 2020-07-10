package com.example.mymisha;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.os.PersistableBundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class ActivityA extends AppCompatActivity {
    TextView messageTodisplay;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activitya);
        messageTodisplay=findViewById(R.id.text2);
        String a=  getIntent().getStringExtra("Message");//pour avoir acces aux messages
        messageTodisplay.setText(a);
    }
}
