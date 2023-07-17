package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.GenericLifecycleObserver;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;
import com.bumptech.glide.Glide;

public class MainActivity extends AppCompatActivity {
    ImageView carga;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        carga = (ImageView) findViewById(R.id.ImgCarga);

        Glide.with(this)
                .asGif()
                .load(R.drawable.cargando)
                .into(carga);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent cambio = new Intent(MainActivity.this, MainActivity2.class);
                startActivity(cambio);
            }
        }, 4000);

    }
}