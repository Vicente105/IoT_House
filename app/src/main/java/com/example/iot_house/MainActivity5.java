package com.example.iot_house;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

public class MainActivity5 extends AppCompatActivity {
    private ImageView btnEncender, btnApagar;
    private String apiUrl = "http://192.168.100.13/api_domotica/update_v_app.php"; // Cambiar por la URL de tu API
    private RequestQueue requestQueue;
    private TextView tvnivea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main5);

        tvnivea = (TextView) findViewById(R.id.tvnivea);

        btnEncender = (ImageView) findViewById(R.id.btnencender);
        btnApagar = (ImageView) findViewById(R.id.btnapagar);

        requestQueue = Volley.newRequestQueue(this);

        btnEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEstadoFoco(1, "Encender");
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEstadoFoco(0, "Apagar");
            }
        });
    }

    private void enviarEstadoFoco(int estado, String accion) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "http://192.168.100.13/api_domotica/update_v_app.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity5.this, accion, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity5.this, "Error. Intente más tarde", Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap();
                parametros.put("pkSensor", "3");
                parametros.put("v_app", estado+""); // 1 o 0
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }
}
