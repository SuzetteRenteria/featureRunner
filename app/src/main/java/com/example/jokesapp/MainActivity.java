package com.example.jokesapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //TODO: Create a main page display because I moved old display
        //      to dadJokesActivity.
    }

    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //connect menu to multiple layouts
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.jokesGame:
                Intent intentJokes = new Intent(MainActivity.this, dadJokesActivity.class);
                startActivity(intentJokes);
                break;
            case R.id.numeralGame:
                Intent intentConverter = new Intent(MainActivity.this, NumeralConverterActivity.class);
                startActivity(intentConverter);
                break;
            case R.id.clickerGame:
                Intent intentClicker = new Intent(MainActivity.this, ClickerGameActivity.class);
                startActivity(intentClicker);
                break;
            case R.id.exit_option:
                finish();
                break;

        }
        return super.onOptionsItemSelected(item);
    }
}


