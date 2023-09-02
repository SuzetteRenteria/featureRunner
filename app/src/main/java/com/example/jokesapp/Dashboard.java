package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


public class Dashboard extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_main);
    }
    //inflate menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.mainmenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //connect menu to multiple layouts
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        /*
        switch (item.getItemId()) {
            case R.id.jokesGame:
                Intent intentJokes = new Intent(Dashboard.this, dadJokesAct.class);
                startActivity(intentJokes);
                finish();
                break;
            case R.id.numeralGame:
                Intent intentConverter = new Intent(Dashboard.this, NumeralConverterAct.class);
                startActivity(intentConverter);
                finish();
                break;
            case R.id.clickerGame:
                Intent intentClicker = new Intent(Dashboard.this, ClickerGameAct.class);
                startActivity(intentClicker);
                finish();
                break;
            case R.id.exit_option:
                finish();
                break;
        }
         */

        if(item.getItemId() == R.id.jokesGame){
            Intent intentJokes = new Intent(Dashboard.this, dadJokesAct.class);
            startActivity(intentJokes);
            finish();
        }else if(item.getItemId() == R.id.numeralGame){
            Intent intentConverter = new Intent(Dashboard.this, NumeralConverterAct.class);
            startActivity(intentConverter);
            finish();
        }else if(item.getItemId() == R.id.clickerGame){
            Intent intentClicker = new Intent(Dashboard.this, ClickerGameAct.class);
            startActivity(intentClicker);
            finish();
        }else if(item.getItemId() == R.id.exit_option){
            finish();
        }




        return super.onOptionsItemSelected(item);
    }
}



