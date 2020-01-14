package com.deepak.sensors;

import androidx.appcompat.app.AppCompatActivity;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class Gyroscope extends AppCompatActivity {

    private TextView tvAngle;
    private SensorManager sensorManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gyroscope);

        tvAngle = findViewById(R.id.tvAngle);


        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);

        Sensor sensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);

        SensorEventListener gyroListener = new SensorEventListener() {
            @Override
            public void onSensorChanged(SensorEvent event) {
                if (event.values[1] < 0){
                    tvAngle.setText("Left");
                }
                else if (event.values[1] > 0){
                    tvAngle.setText("Right");
                }
            }

            @Override
            public void onAccuracyChanged(Sensor sensor, int accuracy) {

            }
        };

        if (sensor != null){
            sensorManager.registerListener(gyroListener, sensor,SensorManager.SENSOR_DELAY_NORMAL);
        }
        else {
            Toast.makeText(this, "No sensor found.", Toast.LENGTH_SHORT).show();
        }

    }
}
