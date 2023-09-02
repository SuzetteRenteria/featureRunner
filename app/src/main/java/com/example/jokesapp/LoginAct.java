package com.example.jokesapp;

//import static androidx.fragment.app.FragmentManager.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class LoginAct extends AppCompatActivity {

    Button login_BTN;
    Button newAcc_BTN;
    TextInputLayout userName_LoginTV;
    TextInputLayout passWord_LoginTV;
    FirebaseAuth mAuth;

    @Override
    public void onStart() {
        super.onStart();
        // Check if user is signed in (non-null) and update UI accordingly.
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            currentUser.reload();
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_login);
        getSupportActionBar().hide();
        mAuth = FirebaseAuth.getInstance(); //init auth
        //FirebaseApp.initializeApp(this);


        login_BTN = findViewById(R.id.login_Btn);
        newAcc_BTN = findViewById(R.id.newAcc_Btn);
        userName_LoginTV = findViewById(R.id.userName_TVLogin);
        passWord_LoginTV = findViewById(R.id.passWord_TVLogin);

        //Login Button
        //TODO: Only go to next view if authentication is approven
        login_BTN.setOnClickListener(v -> {
            //pull string from UI input
            if(userName_LoginTV != null & passWord_LoginTV != null){
                String emailTxt = userName_LoginTV.getEditText().getText().toString().trim();
                String psswrdTxt = passWord_LoginTV.getEditText().getText().toString().trim();

                //show toast if field == empty
                if(TextUtils.isEmpty(emailTxt)){
                    Toast.makeText(LoginAct.this, "Enter Email", Toast.LENGTH_LONG).show();
                    return;

                }
                if(TextUtils.isEmpty(psswrdTxt)){
                    Toast.makeText(LoginAct.this, "Enter Password", Toast.LENGTH_LONG).show();
                    return;
                }

                mAuth.signInWithEmailAndPassword(emailTxt, psswrdTxt)
                        .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(LoginAct.this, "Authentication: SUCCESS.", Toast.LENGTH_SHORT).show();
                                } else {
                                    Log.w(emailTxt, "signInWithEmail:failure", task.getException());
                                    Toast.makeText(LoginAct.this, "Authentication: FAIL.", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });

                Intent intent = new Intent(LoginAct.this, Dashboard.class);
                startActivity(intent);
                finish();

            }else{
                Toast.makeText(LoginAct.this, "Please fill empty fields", Toast.LENGTH_SHORT).show();
            }
        });

        //Create New Account? Button
        newAcc_BTN.setOnClickListener(v -> {
            Intent intent = new Intent(LoginAct.this, SignUpAct.class);
            startActivity(intent);
            finish();
        });
    }
}