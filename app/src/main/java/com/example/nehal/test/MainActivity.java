package com.example.nehal.test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginButtonClicked(View v){
        Intent i= new Intent(MainActivity.this,Login.class);
        startActivity(i);
    }

    public void signUpButtonClicked(View v) {
        Intent i= new Intent(MainActivity.this, Register.class);
        startActivity(i);
    }
}
