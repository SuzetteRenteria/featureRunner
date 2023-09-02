package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpAct extends AppCompatActivity {

    //private SignUpMVC signUpMVC;
    MaterialButton signUp_BTN;
    MaterialButton alrUser_BTN;
    TextInputLayout firstName_SignUpTV;
    TextInputLayout lastName_SignUpTV;
    TextInputLayout userName_SignUpTV;
    TextInputLayout passWord_SignUpTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_signup);
        getSupportActionBar().hide();
        //vmSU = new ViewModelProvider(this).get(SignUpViewModel.class);

        signUp_BTN = findViewById(R.id.signUp_Btn);
        alrUser_BTN = findViewById(R.id.alrUser_Btn);
        firstName_SignUpTV = findViewById(R.id.firstName_TVSignUp);
        lastName_SignUpTV = findViewById(R.id.lastName_TVSignUp);
        userName_SignUpTV = findViewById(R.id.userName_TVSignUp);
        passWord_SignUpTV = findViewById(R.id.password_TVSignUp);

        signUp_BTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //collect input from UI
                if(firstName_SignUpTV != null &
                    lastName_SignUpTV != null &
                    userName_SignUpTV != null &
                    passWord_SignUpTV != null){
                        String fName = firstName_SignUpTV.getEditText().getText().toString().trim();
                        String lName = lastName_SignUpTV.getEditText().getText().toString().trim();
                        String uName = userName_SignUpTV.getEditText().getText().toString().trim();
                        String pWord = passWord_SignUpTV.getEditText().getText().toString().trim();

                        DatabaseReference usersRef = FirebaseDatabase.getInstance().getReference().child("Users");
                        SignUpMVC per = new SignUpMVC(fName, lName, pWord, uName);

                        //Update DB with key-val pair as uName-newPerson
                        usersRef.child(per.getuName()).setValue(per.newPerson());
                        Intent intent = new Intent(SignUpAct.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                }else{
                    Toast.makeText(SignUpAct.this, "Please fill empty fields", Toast.LENGTH_SHORT).show();
                }

            }
        });
    }
}