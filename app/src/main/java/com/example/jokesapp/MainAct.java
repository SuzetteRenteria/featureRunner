/*
package com.example.jokesapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class Intro_Splash_Screen extends AppCompatActivity {

    public static int SPLASH_SCREEN = 5 * 1000;
    //vars
    Animation topAnim;
    Animation bottAnim;
    ImageView img;
    TextView splashTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.app_intro_splash);

        //Anims
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_splah_anim);
        bottAnim = AnimationUtils.loadAnimation(this, R.anim.bott_splash_anim);

        //Hooks
        img = findViewById(R.id.dragon_splash);
        splashTitle = findViewById(R.id.splash_Title);

        img.setAnimation(topAnim);
        img.setAnimation(bottAnim);
        splashTitle.setAnimation(bottAnim);

        Handler handler = new Handler();
        Runnable runnable = () -> {
            Intent intent = new Intent(Intro_Splash_Screen.this, MainActivity.class);
            startActivity(intent);
            finish();
        };
        handler.postDelayed(runnable, SPLASH_SCREEN);
    }
}
 */
package com.example.jokesapp;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainAct extends AppCompatActivity {

    public static int SPLASH_SCREEN = 5 * 1000;
    //vars
    Animation topAnim;
    Animation bottAnim;
    ImageView img;
    TextView splashTitle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.app_intro_splash);

        //Anims
        topAnim = AnimationUtils.loadAnimation(this, R.anim.top_splah_anim);
        bottAnim = AnimationUtils.loadAnimation(this, R.anim.bott_splash_anim);

        //Hooks
        img = findViewById(R.id.dragon_splash);
        splashTitle = findViewById(R.id.splash_Title);

        img.setAnimation(topAnim);
        img.setAnimation(bottAnim);
        splashTitle.setAnimation(bottAnim);

        Handler handler = new Handler();
        Runnable runnable = () -> {
            Intent intent = new Intent(MainAct.this, LoginAct.class);
            startActivity(intent);
            finish();
        };
        handler.postDelayed(runnable, SPLASH_SCREEN);
    }
}