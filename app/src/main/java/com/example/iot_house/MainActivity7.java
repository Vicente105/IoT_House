package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity7 extends AppCompatActivity {
    ImageView btnriego;
    boolean isOpen = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main7);

        btnriego = findViewById(R.id.btnriego);

        // Establecer la imagen inicial según el estado actual
        if (isOpen) {
            btnriego.setImageResource(R.drawable.encedido);
        } else {
            btnriego.setImageResource(R.drawable.apaga);
        }

        btnriego.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Cambiar el estado y la imagen del botón al hacer clic
                isOpen = !isOpen;
                if (isOpen) {
                    btnriego.setImageResource(R.drawable.encedido);
                } else {
                    btnriego.setImageResource(R.drawable.apaga);
                }
            }
        });
    }
}
