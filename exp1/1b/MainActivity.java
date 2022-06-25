package com.example.exp1;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //actionbar title
        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle("First Activity");
        //EditText
        EditText mName = findViewById(R.id.et_name);
        EditText mEmail = findViewById(R.id.et_email);
        EditText mPhone = findViewById(R.id.et_Phone);
        //Button
        Button mSave = findViewById(R.id.bt_save);
        //Button Click Listener
        mSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) { //get data from edittext
                String name = mName.getText().toString();
                String email = mEmail.getText().toString();
                String phone = mPhone.getText().toString();

                //activity Intent
                Intent intent = new  Intent(MainActivity.this, SecondActivity.class);
                intent.putExtra("NAME", name);
                intent.putExtra("EMAIL", email);
                intent.putExtra("PHONE", phone);
                startActivity(intent);



            }
        });
        }
    }

