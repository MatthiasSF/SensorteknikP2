package com.example.matth.project2.Fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.matth.project2.Controller;
import com.example.matth.project2.R;

/**
 * Fragment that let the user choose whether they want to retrieve the information via volley or asyncTask
 * @author Matthias Falk
 */
public class ApiPickerFragment extends Fragment {
    private Button asyncButton;
    private Button volleyButton;
    private View view;
    private Controller controller;

    /**
     * Basic onCreateView
     * calls initialize();
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return view
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_api_picker, container, false);
        initialize();
        return view;
    }

    /**
     * Initializes all off the components used in the fragment
     */
    private void initialize() {
        asyncButton = view.findViewById(R.id.apButtonAsync);
        volleyButton = view.findViewById(R.id.apButtonVolley);
        asyncButton.setOnClickListener(new ButtonListener());
        volleyButton.setOnClickListener(new ButtonListener());
    }

    /**
     * sets the controller
     * @param controller - an instance of controller
     */
    public void setController(Controller controller){
        this.controller = controller;
    }

    /**
     * Inner class that implements onClickListener
     */
    private class ButtonListener implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.apButtonAsync){
                controller.connectToWeatherReceiver("Async");
            }
            if(v.getId() == R.id.apButtonVolley){
                controller.connectToWeatherReceiver("Volley");
            }
        }
    }
}
