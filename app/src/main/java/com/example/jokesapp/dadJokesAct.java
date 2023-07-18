package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class dadJokesAct extends AppCompatActivity {
    Button btn_j1;
    Button btn_j2;
    Button btn_j1_ans;
    Button btn_j2_ans;
    TextView tv_JokeDisplay;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_dad_jokes);

        btn_j1 = findViewById(R.id.BTN_J1);
        btn_j1_ans = findViewById(R.id.BTN_J1_ANS);
        btn_j2 = findViewById(R.id.BTN_J2);
        btn_j2_ans = findViewById(R.id.BTN_J2_ANS);
        tv_JokeDisplay = findViewById(R.id.tv_JokeDisplay);


        //button Joke 1
        btn_j1.setOnClickListener(v -> tv_JokeDisplay.setText(R.string.j1));

        //button Joke 1 Answer
        btn_j1_ans.setOnClickListener(v -> tv_JokeDisplay.setText(R.string.j1_ANS));

        //button Joke 2
        btn_j2.setOnClickListener(v -> tv_JokeDisplay.setText(R.string.j2));

        //button Joke 2 Answer
        btn_j2_ans.setOnClickListener(v -> tv_JokeDisplay.setText(R.string.j2_ANS));
    }
}