package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.ProgressBar;
import android.widget.TextView;
import com.example.jokesapp.R;

public class ClickerGameActivity extends AppCompatActivity {
    Button clkrBtnA;
    Button clkrBtnB;
    Button clkrStartBtn;
    TextView tv_ClksLeft;
    TextView tv_clkrTimer;
    ProgressBar roundProgBar;
    int clksCntr = 0; // clicks counter
    int secsLim = 20; // starting timer
    private int highScore = 0; // track high score

    public void popUpWin(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View gameOverWin = inflater.inflate(R.layout.clkr_gameover_popupwin, null);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(gameOverWin, width, height, focusable);
        popupWindow.showAtLocation(this.findViewById(R.id.clickerGame), Gravity.CENTER, 0, 0);
    } //Display popup window at game over

    public int getHighScore(){
        return (clksCntr > highScore) ? (highScore = clksCntr):(clksCntr);
    } // compare for high score
    public int incClksCntr(){
        return clksCntr++;
    } // increment count of clicks

    public int decSecsLim(){
        return secsLim--;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        clkrBtnA = (Button) findViewById(R.id.clkrBtnA);
        clkrBtnB = (Button) findViewById(R.id.clkrBtnB);
        clkrStartBtn = (Button) findViewById(R.id.clkrStartBtn);
        tv_ClksLeft = (TextView) findViewById(R.id.tv_ClksLeft);
        tv_clkrTimer = (TextView) findViewById(R.id.tv_clkrTimer);
        roundProgBar = (ProgressBar) findViewById(R.id.roundProgBar);
        final CountDownTimer tmr = new CountDownTimer(20*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int t = decSecsLim();
                String t1 = getString(R.string.timeLeft);
                roundProgBar.setProgress(t * 5);
                roundProgBar.setMax(100);
                tv_clkrTimer.setText(t1 + Integer.toString(t));
            }

            @Override
            public void onFinish() {
                clkrBtnA.setEnabled(false);
                clkrBtnB.setEnabled(false);
                roundProgBar.setProgress(0);
                //add win
                popUpWin();
            }
        };

        clkrStartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clkrBtnA.setEnabled(true);
                clkrBtnB.setEnabled(true);
                clkrStartBtn.setEnabled(false);
                clksCntr = 0;
                secsLim = 20;
                tmr.start();
            }
            public void onFinish(View v){
                // game over
                clkrBtnA.setEnabled(false);
                clkrBtnB.setEnabled(false);
                String t1 = getString(R.string.clkrOVER);
                tv_ClksLeft.setText(t1 + Integer.toString(clksCntr));
            }
        });

        Thread tT2 = new Thread(new Runnable() {
            @Override
            public void run() {
                clkrBtnA.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //clksCntr++;
                        String t2 = getString(R.string.tv_clksLeft);
                        int it2 = incClksCntr();
                        tv_ClksLeft.setText(t2 + Integer.toString(it2));
                    }
                });
            }
        });

        Thread tT = new Thread(new Runnable() {
            @Override
            public void run() {
                clkrBtnB.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //clksCntr++;
                        //incClksCntr(clksCntr);
                        String t3 = getString(R.string.tv_clksLeft);
                        int it3 = incClksCntr();
                        tv_ClksLeft.setText(t3 + Integer.toString(it3));
                    }
                });
            }
        });

        tT2.start();
        tT.start();

        try {
            tT2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        try {
            tT.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}