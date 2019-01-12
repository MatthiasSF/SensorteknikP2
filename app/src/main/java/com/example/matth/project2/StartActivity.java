package com.example.matth.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

/**
 * The Activity that starts the app.
 * @author Matthias Falk
 */
public class StartActivity extends AppCompatActivity {
    private Button btnSensor;
    private Button btnApi;
    private Button btnCompare;
    private Button btnAltitude;

    /**
     * Basic onCreate method
     * Calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
    }

    /**
     * Initializes all of the components used by the activity
     */
    private void initialize(){
        btnApi = findViewById(R.id.startMenuBtn2);
        btnSensor = findViewById(R.id.startMenuBtn1);
        btnCompare = findViewById(R.id.startMenuBtn3);
        btnAltitude = findViewById(R.id.startMenuBtn4);
        btnApi.setOnClickListener(new btnListener());
        btnSensor.setOnClickListener(new btnListener());
        btnCompare.setOnClickListener(new btnListener());
        btnAltitude.setOnClickListener(new btnListener());
    }

    /**
     * Changes the active Activity depending on user choice
     * @param activityType - the type of activyt it will change to
     */
    private void changeActivity(String activityType){
        Intent intent;
        if (activityType == "sensor"){
            intent = new Intent(this, SensorActivity.class);
            this.startActivity(intent);
        }
        else if (activityType == "api"){
            intent = new Intent(this, ApiPickerActivity.class);
            this.startActivity(intent);
        }
        else if(activityType == "compare"){
            intent = new Intent(this, CompareActivity.class);
            this.startActivity(intent);
        }
        else if(activityType == "altitude"){
            intent = new Intent(this, AltitudeActivity.class);
            this.startActivity(intent);
        }
    }

    /**
     * Inner class that implements the OnClickListener interface
     * Calls the changeActivity() method
     */
    private class btnListener implements View.OnClickListener {
        @Override
        public void onClick(View v) {
            if (v.getId() == btnApi.getId()){
                changeActivity("api");
            }
            else if (v.getId() == btnSensor.getId()){
                changeActivity("sensor");
            }
            else if (v.getId() == btnCompare.getId()){
                changeActivity("compare");
            }
            else if(v.getId() == btnAltitude.getId()){
                changeActivity("altitude");
            }
        }
    }
}
