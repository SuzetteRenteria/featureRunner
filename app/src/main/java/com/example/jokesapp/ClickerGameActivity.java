package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.ViewModelProvider;
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

public class ClickerGameActivity extends AppCompatActivity implements LifecycleOwner {

    private ClickerGameViewModel vm;
    Button clkrBtnB;
    Button clkrStartBtn;
    Button clkrReset;
    TextView tv_ClksLeft;
    TextView tv_clkrTimer;
    ProgressBar roundProgBar;

    public void popUpWin(){
        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        ViewGroup parent = findViewById(R.id.clickerGame);
        View gameOverWin = inflater.inflate(R.layout.clkr_gameover_popupwin, parent, false);
        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true;
        final PopupWindow popupWindow = new PopupWindow(gameOverWin, width, height, focusable);
        popupWindow.showAtLocation(parent, Gravity.CENTER, 0, 0);
    } //Display popup window at game over

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clicker_game);
        vm = new ViewModelProvider(this).get(ClickerGameViewModel.class);

        clkrBtnB = findViewById(R.id.clkrBtnB);
        clkrStartBtn = findViewById(R.id.clkrStartBtn);
        clkrReset = findViewById(R.id.resetBtn);
        tv_ClksLeft = findViewById(R.id.tv_ClksLeft);
        tv_clkrTimer = findViewById(R.id.tv_clkrTimer);
        roundProgBar = findViewById(R.id.roundProgBar);


        final CountDownTimer tmr = new CountDownTimer(vm.getsecsLim_DEFAULT() * 1000L, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                int decTemp = vm.decSecsLim();
                final int progMax = 100;
                final int progSetUp = progMax / vm.getsecsLim_DEFAULT();
                roundProgBar.setProgress(decTemp * progSetUp);
                roundProgBar.setMax(progMax);
                tv_clkrTimer.setText(getString(R.string.timerLeft_msg, vm.getSecsLim()));
            }

            @Override
            public void onFinish() {
                clkrBtnB.setEnabled(false);
                roundProgBar.setProgress(0);
                tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, vm.getclksCntr()));
                tv_clkrTimer.setText(getString(R.string.timerLeft_msg, vm.getSecsLim()));
                popUpWin(); // popup window
            }
        };

        clkrStartBtn.setOnClickListener(v ->{
            clkrBtnB.setEnabled(true);
            clkrStartBtn.setEnabled(false);
            vm.clksCntr = vm.getclksCntr_DEFAULT();
            vm.secsLim = vm.getsecsLim_DEFAULT();
            tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, vm.getclksCntr_DEFAULT()));
            tmr.start();
        });


        clkrReset.setOnClickListener(v -> {
            tmr.cancel();

            clkrStartBtn.setEnabled(true);
            vm.setToDefclksCntr();
            vm.setToDefsecsLim();

            tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, vm.getclksCntr()));
            tv_clkrTimer.setText(getString(R.string.timerLeft_msg, vm.getSecsLim()));
            roundProgBar.setProgress(0);
        });

        Thread tT = new Thread(() ->
                clkrBtnB.setOnClickListener(v ->
                        tv_ClksLeft.setText(getString(R.string.clksLeft_msgg, vm.incClksCntr()))
                )
        ); // Button B thread

        tT.start();
    }
}