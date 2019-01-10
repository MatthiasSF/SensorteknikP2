package com.example.matth.project2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ApiPickerActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_picker);
        new Controller(this);
    }
    public void setFragment(Fragment fragment, boolean backStack, String tag){
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();
        ft.replace(R.id.apiFrameLayout,fragment,tag);
        if (backStack){
            ft.addToBackStack(null);
        }
        ft.commit();
    }
}
