package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    ImageView ivnagua, ivgaraje, ivluz, ivventilador, ivriego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ivnagua = (ImageView) findViewById(R.id.ivnagua);
        ivgaraje = (ImageView) findViewById(R.id.ivgaraje);
        ivluz = (ImageView) findViewById(R.id.ivluz);
        ivventilador = (ImageView) findViewById(R.id.ivventilador);
        ivriego = (ImageView) findViewById(R.id.ivriego);

        ivnagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivgaraje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivluz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivventilador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ivriego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }
}