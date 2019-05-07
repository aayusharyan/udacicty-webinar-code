package com.aayushsinha.android.intenttestudacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AnotherActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_another);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("AdditionalMessage");

        TextView textView = (TextView)findViewById(R.id.textView);
        textView.setText(msg);
    }
}
