package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity6 extends AppCompatActivity {
    TextView tvnivel, tvnivev;
    SeekBar sbtemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main6);

        tvnivel = (TextView) findViewById(R.id.tvnivel);
        tvnivev = (TextView) findViewById(R.id.tvnivev);

        sbtemp = (SeekBar) findViewById(R.id.sbtemp);

        sbtemp.setMax(10);
        sbtemp.setProgress(0);
        sbtemp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                tvnivel.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}