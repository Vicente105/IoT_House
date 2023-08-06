package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity2 extends AppCompatActivity {
    ImageView ivnagua,ivluz, ivventilador, ivriego;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        ivnagua = (ImageView) findViewById(R.id.ivnagua);
        ivluz = (ImageView) findViewById(R.id.ivluz);
        ivventilador = (ImageView) findViewById(R.id.ivventilador);
        ivriego = (ImageView) findViewById(R.id.ivriego);

        ivnagua.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio=new Intent(MainActivity2.this, MainActivity5.class);
                startActivity(inicio);
            }
        });

        ivluz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio=new Intent(MainActivity2.this, MainActivity3.class);
                startActivity(inicio);

            }
        });

        ivventilador.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio=new Intent(MainActivity2.this, MainActivity6.class);
                startActivity(inicio);

            }
        });

        ivriego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent inicio=new Intent(MainActivity2.this, MainActivity7.class);
                startActivity(inicio);

            }
        });
    }
}