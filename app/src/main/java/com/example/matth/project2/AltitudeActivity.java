package com.example.matth.project2;

import android.hardware.SensorManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Activity that calculates the users altitude using the sensor pressure_standard_atmosphere
 * @author Matthias Falk
 */
public class AltitudeActivity extends AppCompatActivity {
    private TextView altitudeTv;
    private Controller controller;

    /**
     * Basic onCreate method. Calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_altitude);
        initialize();
    }

    /**
     * Initializes all of the components used by the activity
     */
    private void initialize(){
        this.altitudeTv = findViewById(R.id.altitudeTv);
        controller = new Controller(this);
        controller.connectToWeatherReceiver("Async");
    }

    /**
     * Gets an float from the sensor and formats it.
     * @param text
     */
    public void setText(String text){
        float altitude = SensorManager.getAltitude(SensorManager.PRESSURE_STANDARD_ATMOSPHERE, Float.parseFloat(text));
        NumberFormat formatter = new DecimalFormat("#0.0");
        altitudeTv.setText(String.valueOf(formatter.format(altitude)) + " m");
    }
}
