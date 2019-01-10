package com.example.matth.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class SensorActivity extends AppCompatActivity {
    private TextView tempTV;
    private TextView humidityTV;
    private TextView pressureTV;
    private SensorReceiver sensorReceiver;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sensor);
        initialize();
    }
    private void initialize(){
        tempTV = findViewById(R.id.tempTV);
        pressureTV = findViewById(R.id.pressureTV);
        humidityTV = findViewById(R.id.humidityTV);
        tempTV.setText(R.string.not_available);
        pressureTV.setText(R.string.not_available);
        humidityTV.setText(R.string.not_available);
        sensorReceiver = new SensorReceiver(this);
    }
    public void setTempText(String temp){
        tempTV.setText(temp);
    }
    public void setPressureText(String pressure){
        pressureTV.setText(pressure);
    }
    public void setHumidityText(String humidity){
        humidityTV.setText(humidity);
    }
    @Override
    protected void onResume() {
        super.onResume();
        sensorReceiver = new SensorReceiver(this);
        Toast.makeText(this, "Listener registered", Toast.LENGTH_LONG).show();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorReceiver.deRegister();
        Toast.makeText(this, "Listener unregistered", Toast.LENGTH_LONG).show();
    }
}
