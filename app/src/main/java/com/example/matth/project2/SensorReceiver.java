package com.example.matth.project2;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorReceiver {
    private SensorManager sensorManager;
    private Sensor temperatureSensor;
    private Sensor pressureSensor;
    private Sensor humiditySensor;
    private SensorActivity sensorActivity;
    private CompareActivity compareActivity;

    public SensorReceiver(SensorActivity sensorActivity){
        this.sensorActivity = sensorActivity;
        setSensors();
    }
    public SensorReceiver(CompareActivity context){
        this.compareActivity = context;
    }
    public void deRegister() {
        sensorManager = null;
        temperatureSensor = null;
        humiditySensor = null;
        pressureSensor = null;
    }
    public void setText(String type, String text){
        if (sensorActivity != null){
            if (type.equals("Temp")){
                sensorActivity.setTempText(text + " c");
            }
            if (type.equals("Humidity")){
                sensorActivity.setHumidityText(text + "%");
            }
            if (type.equals("Pressure")){
                sensorActivity.setPressureText(text + " hPa");
            }
        }
        if (compareActivity != null){
            if (type.equals("Temp")){
                compareActivity.setTempSensor(text);
            }
            if (type.equals("Humidity")){
                compareActivity.setHumSensor(text);
            }
            if (type.equals("Pressure")){
                compareActivity.setPresSensor(text);
            }
        }
    }
    public void setSensors(){
        if (compareActivity != null) {
            sensorManager = (SensorManager) compareActivity.getSystemService(Context.SENSOR_SERVICE);
        }
        if (sensorActivity != null){
            sensorManager = (SensorManager) sensorActivity.getSystemService(Context.SENSOR_SERVICE);
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) != null) {
            temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE);
            sensorManager.registerListener(new SensorListener(), temperatureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE) == null){
            setText("Temp",String.valueOf(R.string.not_available));
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) != null){
            pressureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE);
            sensorManager.registerListener(new SensorListener(), pressureSensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if(sensorManager.getDefaultSensor(Sensor.TYPE_PRESSURE) == null){
            setText("Pressure", String.valueOf(R.string.not_available));
        }
        if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) != null){
            humiditySensor = sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY);
            sensorManager.registerListener(new SensorListener(), humiditySensor, SensorManager.SENSOR_DELAY_NORMAL);
        }
        else if (sensorManager.getDefaultSensor(Sensor.TYPE_RELATIVE_HUMIDITY) == null){
            setText("Humidity", String.valueOf(R.string.not_available));
        }
    }
    private class SensorListener implements SensorEventListener {
        @Override
        public void onSensorChanged(SensorEvent event) {
            if (event.sensor.getType() ==
                    Sensor.TYPE_RELATIVE_HUMIDITY) {
                setText("Humidity",String.valueOf(event.values[0]));
            }
            else if (event.sensor.getType() ==
                    Sensor.TYPE_AMBIENT_TEMPERATURE) {
                setText("Temp", String.valueOf(event.values[0]));
            }
            else if (event.sensor.getType() ==
                    Sensor.TYPE_PRESSURE) {
                setText("Pressure", String.valueOf(event.values[0]));
            }
        }
        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    }
}
