package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginAct extends AppCompatActivity {

    Button login_BTN;
    Button newAcc_BTN;
    TextView userName_LoginTV;
    TextView passWord_LoginTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_login);
        getSupportActionBar().hide();

        login_BTN = findViewById(R.id.login_Btn);
        newAcc_BTN = findViewById(R.id.newAcc_Btn);
        userName_LoginTV = findViewById(R.id.userName_LoginInp);
        passWord_LoginTV = findViewById(R.id.passWord_LoginInp);

        login_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAct.this, Dashboard.class);
            startActivity(intent);
            finish();
        });

        newAcc_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAct.this, SignUpAct.class);
            startActivity(intent);
            finish();
        });
    }
}