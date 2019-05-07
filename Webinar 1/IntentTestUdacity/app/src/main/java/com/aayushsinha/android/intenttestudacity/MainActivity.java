package com.aayushsinha.android.intenttestudacity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startIntent(View view) {
        EditText editText = (EditText)findViewById(R.id.editText);
        String msg = editText.getText().toString();
        Intent intent = new Intent(getApplicationContext(), AnotherActivity.class);
        intent.putExtra("AdditionalMessage", msg);
        startActivity(intent);
    }

    public void shareIntent(View view) {
        EditText editText = (EditText)findViewById(R.id.editText);
        String msg = editText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_TEXT, msg);
        startActivity(Intent.createChooser(intent, "Share Via Test"));
    }
}
