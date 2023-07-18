package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SignUpAct extends AppCompatActivity {

    Button signUp_BTN;
    Button alrUser_BTN;
    TextView firstName_SignUpTV;
    TextView lastName_SignUpTV;
    TextView userName_SignUpTV;
    TextView passWord_SignUpTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_signup);
        getSupportActionBar().hide();

        signUp_BTN = findViewById(R.id.signUp_Btn);
        alrUser_BTN = findViewById(R.id.alrUser_Btn);
        firstName_SignUpTV = findViewById(R.id.fName_SignUpInp);
        lastName_SignUpTV = findViewById(R.id.lName_SignUpInp);
        userName_SignUpTV = findViewById(R.id.userName_SignUpInp);
        passWord_SignUpTV = findViewById(R.id.passWord_SignUpInp);

        signUp_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(SignUpAct.this, Dashboard.class);
            startActivity(intent);
            finish();
        });
    }
}