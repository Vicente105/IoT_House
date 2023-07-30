package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity4 extends AppCompatActivity {
    Button btngaraje;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        btngaraje = (Button) findViewById(R.id.btngaraje);

        // Establecer la imagen inicial según el estado actual
        if (isOpen) {
            btngaraje.setBackgroundResource(R.drawable.abierto);
        } else {
            btngaraje.setBackgroundResource(R.drawable.cerrado);
        }

        btngaraje.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar el estado y la imagen del botón al hacer clic
                isOpen = !isOpen;
                if (isOpen) {
                    btngaraje.setBackgroundResource(R.drawable.abierto);
                } else {
                    btngaraje.setBackgroundResource(R.drawable.cerrado);
                }
            }
        });
    }
}