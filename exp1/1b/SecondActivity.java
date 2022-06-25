package com.example.exp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        //actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("Second Activity");
        //intent to get data
        Intent intent = getIntent();
        String name = intent.getStringExtra("NAME");
        String email = intent.getStringExtra("EMAIL");
        String phone = intent.getStringExtra("PHONE");

        //Textview
        TextView mresult = findViewById(R.id.result);
        //setText
        mresult.setText("Name: "+name+ "\nEmail: "+email+ "\nPhone:"+phone);
    }
}

