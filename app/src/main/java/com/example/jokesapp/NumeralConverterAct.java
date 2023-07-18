package com.example.jokesapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class NumeralConverterAct extends AppCompatActivity {

    Button dec_to_rom_BTN;
    Button rom_to_dec_BTN;
    EditText dec_to_rom_txtBox;
    EditText rom_to_dec_txtBox;
    TextView tv_Rom;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.lay_numeralconvert);

        dec_to_rom_BTN = (Button) findViewById(R.id.dec_to_rom_BTN);
        rom_to_dec_BTN = (Button) findViewById(R.id.rom_to_dec_BTN);
        dec_to_rom_txtBox = (EditText) findViewById(R.id.dec_to_rom_txtBox);
        rom_to_dec_txtBox = (EditText) findViewById(R.id.rom_to_dec_txtBox);
        tv_Rom = findViewById(R.id.tv_Rom);


        dec_to_rom_BTN.setOnClickListener(v -> {
            new Thread(() -> {
                numberConverter nc = new numberConverter();
                int userInput = Integer.parseInt(dec_to_rom_txtBox.getText().toString());
                String romanResult = nc.decToRom(userInput);
                tv_Rom.post(() -> tv_Rom.setText(romanResult));
            }).start();
        });

        rom_to_dec_BTN.setOnClickListener(v -> new Thread(() ->
        {
            numberConverter nc = new numberConverter();
            String userInput = rom_to_dec_txtBox.getText().toString();
            String decimalResult = Integer.toString(nc.romToDec(userInput));
            tv_Rom.post(new Runnable() {
                @Override
                public void run() {
                    tv_Rom.setText(decimalResult);
                }
            });
        }).start());
    }
}
