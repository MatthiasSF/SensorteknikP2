package com.example.matth.project2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import java.text.DecimalFormat;
import java.text.NumberFormat;

/**
 * Activity that displays and comparison between the information recieved from the sensor and the API
 * @author Matthias Falk
 */
public class CompareActivity extends AppCompatActivity {
    private TextView tempApi;
    private TextView tempSensor;
    private TextView tempDifference;
    private TextView humApi;
    private TextView humSensor;
    private TextView humDifference;
    private TextView presApi;
    private TextView presSensor;
    private TextView presDifference;
    private String tempApiText;
    private String humApiText;
    private String presApiText;
    private SensorReceiver sensorReceiver;
    private Controller controller;

    /**
     * Basic onCreate method
     * Calls initialize()
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare);
        initialize();
    }

    /**
     * Initializes all of the components used by the activity
     */
    private void initialize(){
        tempApi = findViewById(R.id.compare_apiValueTemp);
        tempSensor = findViewById(R.id.compare_sensorValueTemp);
        tempDifference = findViewById(R.id.compare_tempDifference);
        humApi = findViewById(R.id.compare_apiValueHum);
        humSensor = findViewById(R.id.compare_sensorValueHum);
        humDifference = findViewById(R.id.compare_humDifference);
        presApi = findViewById(R.id.compare_apiValuePres);
        presSensor = findViewById(R.id.compare_sensorValuePres);
        presDifference = findViewById(R.id.compare_presDiffence);
        sensorReceiver = new SensorReceiver(this);
        sensorReceiver.setSensors();
        controller = new Controller(this);
        controller.connectToWeatherReceiver("Async");
    }

    /**
     * Sets up all of the textviews
     * @param values - array that contains all of the different values from the api
     */
    public void setText(String[] values){
        tempApiText = values[0];
        humApiText = values[2];
        presApiText = values[1];
        tempApi.setText("From api: " + tempApiText + " c");
        humApi.setText("From api: " + humApiText + " %");
        presApi.setText("From api: " + presApiText + " hPa");
        setTempDifference();
        setHumDifference();
        setPresDifference();
    }

    /**
     * sets the text that contains the temparature from the sensor
     * @param text - the text from the sensor
     */
    public void setTempSensor(String text){
        tempSensor.setText("From sensor: " + text + " c");
        setTempDifference();
    }

    /**
     * sets the text that contains the humidity from the sensor
     * @param text - the text from the sensor
     */
    public void setHumSensor(String text){
        humSensor.setText("From sensor: " + text + " %");
        setHumDifference();
    }

    /**
     * sets the text that contains the pressure from the sensor
     * @param text - the text from the sensor
     */
    public void setPresSensor(String text){
        presSensor.setText("From sensor: " + text + " hPa");
        setPresDifference();
    }

    /**
     * Calculates the difference of the temperature readings
     */
    public void setTempDifference(){
        if (!tempSensor.getText().equals(R.string.not_available) && tempApi.getText().length()> 0){
            String sensorText = (String) tempSensor.getText();
            String[] splittedSensor = sensorText.split(" ");
            double sensorValue = Double.parseDouble(splittedSensor[2]);
            String apiText = (String) tempApi.getText();
            String[] splittedApi = apiText.split(" ");
            double apiValue = Double.parseDouble(splittedApi[2]);
            NumberFormat formatter = new DecimalFormat("#0.0");
            tempDifference.setText("Difference: ");
            if (apiValue > sensorValue) {
                tempDifference.append(String.valueOf(formatter.format(apiValue-sensorValue)));
            }
            else{
                tempDifference.append(String.valueOf(formatter.format(sensorValue-apiValue)));
            }
        }
        else if (tempSensor.getText().equals(R.string.not_available)){
            tempDifference.append(tempApi.getText());
        }
        else{
            tempDifference.append(tempSensor.getText());
        }
        tempDifference.append(" c");
    }

    /**
     * Calculates the difference of the humidity readings
     */
    public void setHumDifference(){
        if (!humSensor.getText().equals(R.string.not_available ) && humApi.getText().length()> 0){
            String sensorText = (String) humSensor.getText();
            String[] splittedSensor = sensorText.split(" ");
            double sensorValue = Double.parseDouble(splittedSensor[2]);
            String apiText = (String) humApi.getText();
            String[] splittedApi = apiText.split(" ");
            double apiValue = Double.parseDouble(splittedApi[2]);
            humDifference.setText("Difference: ");
            NumberFormat formatter = new DecimalFormat("#0.0");
            if (apiValue > sensorValue) {
                humDifference.append(String.valueOf(formatter.format(apiValue-sensorValue)));
            }
            else{
                humDifference.append(String.valueOf(sensorValue-apiValue));
            }
        }
        else if (humSensor.getText().equals(R.string.not_available)){
            humDifference.append(humApi.getText());
        }
        else{
            humDifference.append(humSensor.getText());
        }
        humDifference.append(" %");
    }

    /**
     * Calculates the difference of the pressure readings
     */
    public void setPresDifference(){
        if (!presSensor.getText().equals(R.string.not_available) && presApi.getText().length()> 0){
            String sensorText = (String) presSensor.getText();
            String[] splittedSensor = sensorText.split(" ");
            double sensorValue = Double.parseDouble(splittedSensor[2]);
            String apiText = (String) presApi.getText();
            String[] splittedApi = apiText.split(" ");
            presDifference.setText("Difference: ");
            NumberFormat formatter = new DecimalFormat("#0.0");
            double apiValue = Double.parseDouble(splittedApi[2]);
            if (apiValue > sensorValue) {
                presDifference.append(String.valueOf(formatter.format(apiValue-sensorValue)));
            }
            else{
                presDifference.append(String.valueOf(sensorValue-apiValue));
            }
        }
        else if (presSensor.getText().equals(R.string.not_available)){
            presDifference.append(presApi.getText());
        }
        else{
            presDifference.append(presSensor.getText());
        }
        presDifference.append(" hPa");
    }

    /**
     * onResume method
     */
    @Override
    protected void onResume() {
        super.onResume();
        sensorReceiver = new SensorReceiver(this);
        Toast.makeText(this, "Listener registered", Toast.LENGTH_LONG).show();
    }

    /**
     * onDestroy method
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();
        sensorReceiver.deRegister();
        Toast.makeText(this, "Listener unregistered", Toast.LENGTH_LONG).show();
    }
}
