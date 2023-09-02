package com.example.jokesapp;

import android.app.Application;
import com.google.firebase.FirebaseApp;

public class featureRunner extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
