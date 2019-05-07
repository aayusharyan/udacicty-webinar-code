package com.aayushsinha.android.recyclerviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        Intent intent = getIntent();
        String msg = intent.getStringExtra("EXTRA_NAME");
        TextView textView = findViewById(R.id.textView3);
        textView.setText(msg);

    }
}
