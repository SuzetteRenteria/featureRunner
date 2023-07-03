package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
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
    Button clkrBtnB;
    Button clkrStartBtn;
    Button clkrReset;
    TextView tv_ClksLeft;
    TextView tv_clkrTimer;
    ProgressBar roundProgBar;
    static final int clksCntr_DEFAULT = 0;
    int clksCntr = clksCntr_DEFAULT; // clicks counter
    static final int secsLim_DEFAULT = 20; // for this game I decided 20 sec range
    int secsLim = secsLim_DEFAULT; // starting timer
    private int highScore = 0; // track high score

    public void popUpWin(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View gameOverWin = inflater.inflate(R.layout.clkr_gameover_popupwin, findViewById(R.id.clickerGame));
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
    } // decrement timer val to 0

    public void setToDefclksCntr(){ clksCntr = clksCntr_DEFAULT; }
    public void setToDefsecsLim(){ secsLim = secsLim_DEFAULT; }
    public int getclksCntr(){
        return clksCntr;
    }
    public int getSecsLim(){
        return secsLim;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);

        clkrBtnB = (Button) findViewById(R.id.clkrBtnB);
        clkrStartBtn = (Button) findViewById(R.id.clkrStartBtn);
        clkrReset = (Button) findViewById(R.id.resetBtn);
        tv_ClksLeft = (TextView) findViewById(R.id.tv_ClksLeft);
        tv_clkrTimer = (TextView) findViewById(R.id.tv_clkrTimer);
        roundProgBar = (ProgressBar) findViewById(R.id.roundProgBar);
        final CountDownTimer tmr = new CountDownTimer(secsLim_DEFAULT * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                //int t = decSecsLim();
                roundProgBar.setProgress(decSecsLim() * 5);
                roundProgBar.setMax(100);
                tv_clkrTimer.setText(getString(R.string.timerLeft_msg, getSecsLim()));
            }

            @Override
            public void onFinish() {
                clkrBtnB.setEnabled(false);
                roundProgBar.setProgress(0);
                tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, getclksCntr()));
                tv_clkrTimer.setText(getString(R.string.timerLeft_msg, getSecsLim()));
                popUpWin(); // popup window
            }
        };

        clkrStartBtn.setOnClickListener(v ->{
            clkrBtnB.setEnabled(true);
            clkrStartBtn.setEnabled(false);
            clksCntr = clksCntr_DEFAULT;
            secsLim = secsLim_DEFAULT;
            tmr.start();
        });


        clkrReset.setOnClickListener(v -> {
            tmr.cancel();

            clkrStartBtn.setEnabled(true);
            setToDefclksCntr();
            setToDefsecsLim();

            tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, getclksCntr()));
            tv_clkrTimer.setText(getString(R.string.timerLeft_msg, getSecsLim()));
            roundProgBar.setProgress(0);
        });

        Thread tT = new Thread(() ->
                clkrBtnB.setOnClickListener(v ->
                        tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, incClksCntr()))
                )
        ); // Button B thread

        tT.start();

    }
}