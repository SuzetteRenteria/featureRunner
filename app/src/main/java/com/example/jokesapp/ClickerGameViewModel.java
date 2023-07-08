package com.example.jokesapp;

import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;

import androidx.lifecycle.ViewModel;

public class ClickerGameViewModel extends ViewModel {
    static final int clksCntr_DEFAULT = 0;
    int clksCntr = clksCntr_DEFAULT; // clicks counter
    static final int secsLim_DEFAULT = 20; // for this game I decided 20 sec range
    int secsLim = secsLim_DEFAULT; // starting timer
    private int highScore = 0; // track high score


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
    public int getclksCntr_DEFAULT(){
        return clksCntr_DEFAULT;
    }
    public void setToDefsecsLim(){ secsLim = secsLim_DEFAULT; }
    public int getsecsLim_DEFAULT(){
        return secsLim_DEFAULT;
    }
    public int getclksCntr(){
        return clksCntr;
    }
    public int getSecsLim(){
        return secsLim;
    }

}
