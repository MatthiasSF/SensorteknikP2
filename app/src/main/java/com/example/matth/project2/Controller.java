package com.example.matth.project2;

import android.os.Bundle;
import com.example.matth.project2.Fragments.ApiPickerFragment;
import com.example.matth.project2.Fragments.ApiValuesFragment;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Calendar;

public class Controller {
    private static final String API_PICKER_FRAGMENT = "ApiPickerFragment";
    private static final String API_VALUES_FRAGMENT = "ApiValuesFragment";
    private ApiPickerActivity apiPickerActivity;
    private ApiPickerFragment apiPickerFragment;
    private ApiValuesFragment apiValuesFragment;
    private ADIReceiver weatherReceiver;
    private CompareActivity compareActivity;
    private AltitudeActivity altitudeActivity;
    private String[] values;

    public Controller (ApiPickerActivity apiPickerActivity){
        this.apiPickerActivity = apiPickerActivity;
        weatherReceiver = new ADIReceiver(this);
        initializeApiPickerFragment();
        initializeApiValuesFragment();
    }
    public Controller(CompareActivity compareActivity){
        this.compareActivity = compareActivity;
        weatherReceiver = new ADIReceiver(this);
    }
    public Controller(AltitudeActivity altitudeActivity){
        this.altitudeActivity = altitudeActivity;
        weatherReceiver = new ADIReceiver(this);
    }
    private void initializeApiPickerFragment(){
        apiPickerFragment = (ApiPickerFragment) apiPickerActivity.getSupportFragmentManager().findFragmentByTag(API_PICKER_FRAGMENT);
        if (apiPickerFragment == null){
            apiPickerFragment = new ApiPickerFragment();
        }
        apiPickerFragment.setController(this);
        apiPickerActivity.setFragment(apiPickerFragment, false, API_PICKER_FRAGMENT);
    }
    private void initializeApiValuesFragment(){
        apiValuesFragment = (ApiValuesFragment) apiPickerActivity.getSupportFragmentManager().findFragmentByTag(API_VALUES_FRAGMENT);
        if (apiValuesFragment == null){
            apiValuesFragment = new ApiValuesFragment();
        }
    }
    public void connectToWeatherReceiver(String type){
        if (type.equals("Async")) {
            weatherReceiver.startAsync("Malmö");
        }
        if (type.equals("Volley")){
            weatherReceiver.startVolley("Malmö", apiPickerActivity);
        }
    }
    public void jsonReader(JSONObject data, String type){
        try {
            JSONObject main = data.getJSONObject("main");
            JSONArray weather = data.getJSONArray("weather");
            String weatherString = weather.getString(0);
            String[] split = weatherString.split(",");
            weatherString = split[3];
            String img = weatherString.substring(8,11);
            String temp = main.getString("temp");
            String pressure = main.getString("pressure");
            String humidity = main.getString("humidity");
            String timeStamp = String.valueOf(Calendar.getInstance().getTime());
            String[] splitted = timeStamp.split(" ");
            values = new String[]{celsiusConverter(temp), pressure, humidity, splitted[3], img};
            Bundle bundle = new Bundle();
            bundle.putStringArray("values", values);
            bundle.putString("type", type);
            if (apiPickerActivity != null) {
                apiValuesFragment.setArguments(bundle);
                apiPickerActivity.setFragment(apiValuesFragment, true, API_VALUES_FRAGMENT);
            }
            if (compareActivity != null){
                compareActivity.setText(values);
            }
            if (altitudeActivity != null){
                altitudeActivity.setText(pressure);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public String celsiusConverter(String temp){
        double k = Double.parseDouble(temp);
        double c = k - 273.15;
        NumberFormat formatter = new DecimalFormat("#0.0");
        return String.valueOf(formatter.format(c));
    }
}
