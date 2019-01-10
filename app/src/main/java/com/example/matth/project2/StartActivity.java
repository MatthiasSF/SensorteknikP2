package com.example.matth.project2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class StartActivity extends AppCompatActivity {
    private Button btnSensor;
    private Button btnApi;
    private Button btnCompare;
    private Button btnAltitude;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
    }
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
