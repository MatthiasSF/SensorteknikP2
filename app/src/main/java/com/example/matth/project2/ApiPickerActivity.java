package com.example.matth.project2;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Activity that is the parent to all of the fragment used in the app. Handles the transactions between the different fragments.
 * @author Matthias Falk
 */
public class ApiPickerActivity extends AppCompatActivity {

    /**
     * Basic onCreate method
     * Starts up the controller used by the fragments
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_api_picker);
        new Controller(this);
    }

    /**
     * Sets up and displays the new fragment
     * @param fragment - the fragment that will be displayed
     * @param backStack
     * @param tag - tag of the fragment
     */
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
