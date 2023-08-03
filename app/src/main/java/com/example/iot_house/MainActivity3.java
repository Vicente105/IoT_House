package com.example.iot_house;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.SeekBar;
import android.widget.TextView;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallback;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;

public class MainActivity3 extends AppCompatActivity {
    TextView tvnivel;
    SeekBar sbluz;
    MqttAndroidClient mqttClient;
    String brokerUrl = "tcp://direccion_del_broker_mqtt:1883"; // Cambiar por la dirección del broker MQTT

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        tvnivel = findViewById(R.id.tvnivel);
        sbluz = findViewById(R.id.sbluz);

        sbluz.setMax(1023);
        sbluz.setProgress(0);
        sbluz.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                tvnivel.setText(String.valueOf(progress));
                publishIntensity(String.valueOf(progress));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        // Conectarse al broker MQTT
        connectMqttBroker();
    }

    private void connectMqttBroker() {
        String clientId = MqttClient.generateClientId();
        mqttClient = new MqttAndroidClient(this, brokerUrl, clientId);

        try {
            MqttConnectOptions options = new MqttConnectOptions();
            IMqttToken token = mqttClient.connect(options);
            token.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Suscribirse al canal del foco
                    subscribeToFocoChannel();
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Manejar la conexión fallida
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void subscribeToFocoChannel() {
        String topic = "foco/intensidad"; // Canal al que se suscribirá la Raspberry Pi Pico
        int qos = 1;

        try {
            IMqttToken subToken = mqttClient.subscribe(topic, qos);
            subToken.setActionCallback(new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    // Manejar la suscripción exitosa
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {
                    // Manejar la suscripción fallida
                }
            });
            mqttClient.setCallback(new MqttCallback() {
                @Override
                public void connectionLost(Throwable cause) {
                    // Manejar la pérdida de conexión
                }

                @Override
                public void messageArrived(String topic, MqttMessage message) throws Exception {
                    // Manejar el mensaje recibido (opcional, si deseas recibir información del estado de la Raspberry Pi Pico)
                }

                @Override
                public void deliveryComplete(IMqttDeliveryToken token) {
                    // Manejar la entrega del mensaje (opcional, si deseas recibir información del estado de la Raspberry Pi Pico)
                }
            });
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    private void publishIntensity(String intensity) {
        String topic = "foco/intensidad"; // Canal al que se suscribirá la Raspberry Pi Pico
        int qos = 1;

        try {
            MqttMessage message = new MqttMessage(intensity.getBytes());
            message.setQos(qos);
            mqttClient.publish(topic, message);
        } catch (MqttException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mqttClient != null) {
            try {
                mqttClient.disconnect();
            } catch (MqttException e) {
                e.printStackTrace();
            }
        }
    }
}
