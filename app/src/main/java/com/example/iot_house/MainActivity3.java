package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class MainActivity3 extends AppCompatActivity {
    private ImageView btnEncender, btnApagar, btnControlEsp;
    private RequestQueue requestQueue;
    private String apiUrl = "https://gopestudiante.000webhostapp.com/api_domotica/update_v_app.php"; // Cambiar por la URL de tu API
    private String espUrl = "http://192.168.1.100"; // Cambiar por la IP del ESP8266

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        btnEncender = (ImageView) findViewById(R.id.btnencender);
        btnApagar = (ImageView) findViewById(R.id.btnapagar);


        requestQueue = Volley.newRequestQueue(this);

        btnEncender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEstadoFocoApi(1, "Encender");
                enviarComandoEsp("encender");
            }
        });

        btnApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarEstadoFocoApi(0, "Apagar");
                enviarComandoEsp("apagar");
            }
        });
    }

    private void enviarEstadoFocoApi(int estado, String accion) {
        StringRequest stringRequest = new StringRequest(Request.Method.POST, "https://gopestudiante.000webhostapp.com/api_domotica/update_v_app.php",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity3.this, accion + " - API", Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity3.this, "Error en la API. Intente más tarde", Toast.LENGTH_SHORT).show();
                    }
                }) {

            @Override
            protected Map<String, String> getParams() {
                Map<String, String> parametros = new HashMap<>();
                parametros.put("pkSensor", "2");
                parametros.put("v_app", estado + ""); // 1 o 0
                return parametros;
            }
        };
        requestQueue.add(stringRequest);
    }

    private void enviarComandoEsp(String comando) {
        StringRequest stringRequest = new StringRequest(Request.Method.GET, espUrl + "/" + comando,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(MainActivity3.this, "Comando enviado al ESP8266: " + comando, Toast.LENGTH_SHORT).show();
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(MainActivity3.this, "Error al enviar comando al ESP8266", Toast.LENGTH_SHORT).show();
                    }
                });
        requestQueue.add(stringRequest);
    }
}