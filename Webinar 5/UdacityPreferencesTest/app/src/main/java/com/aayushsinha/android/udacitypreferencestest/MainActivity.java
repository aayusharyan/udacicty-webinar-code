package com.aayushsinha.android.udacitypreferencestest;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.StateSet;
import android.widget.CompoundButton;
import android.widget.Switch;

public class MainActivity extends AppCompatActivity {

    private static String PREF_KEY_USERNAME = "PREF_KEY_USERNAME";
    private static String PREF_KEY_SWITCH = "PREF_KEY_SWITCH";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(this);
        String username = pref.getString(PREF_KEY_USERNAME, "Unknown");
        Log.d("PREF_TEST", username);


        SharedPreferences.Editor editor = pref.edit();
        editor.putString(PREF_KEY_USERNAME, "Aayush");
        editor.apply();

        Boolean checked = pref.getBoolean(PREF_KEY_SWITCH, false);
        Switch sw = findViewById(R.id.switch1);
        sw.setChecked(checked);


        sw.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                SharedPreferences.Editor editor = pref.edit();
                editor.putBoolean(PREF_KEY_SWITCH, isChecked);
                editor.apply();
            }
        });
    }
}
