package com.aayushsinha.android.recyclerviewtest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        String[] myDataset = new String[4];
        myDataset[0] = "test";
        myDataset[1] = "another_elemtn";
        myDataset[2] = "Element with Space";
        myDataset[3] = "Finally ";


        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);

        // use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        recyclerView.setHasFixedSize(true);

        // use a linear layout manager
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        // specify an adapter (see also next example)
        MyAdapter mAdapter = new MyAdapter(myDataset);
        recyclerView.setAdapter(mAdapter);

//        Intent intent = new Intent(this, DetailActivity.class);
//        startActivity(intent);
    }
}
