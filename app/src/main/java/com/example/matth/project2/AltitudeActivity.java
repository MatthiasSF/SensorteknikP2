package com.example.matth.project2;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;

public class AltitudeActivity extends AppCompatActivity {
    private TextView altitudeTv;
    private Controller controller;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altitude);
        initialize();
    }
    private void initialize(){
        this.altitudeTv = findViewById(R.id.altitudeTv);
        controller = new Controller(this);
        controller.connectToWeatherReceiver("Async");
    }
    public void setText(String text){
        float altitude = SensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, Float.parseFloat(text));
        NumberFormat formatter = new DecimalFormat("#0.0");
        altitudeTv.setText(String.valueOf(formatter.format(altitude)) + " m");
    }
}
