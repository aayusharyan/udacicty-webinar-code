package com.aayushsinha.android.recyclerviewtest;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    private String[] mDataset;

    public static class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // each data item is just a string in this case
        public TextView textView;
        public Context context;
        public MyViewHolder(View v) {
            super(v);
            textView = (TextView) v.findViewById(R.id.textView2);
            context = v.getContext();
            v.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            intent =  new Intent(context, DetailActivity.class);
            String msg = ((TextView)v.findViewById(R.id.textView2)).getText().toString();
            intent.putExtra("EXTRA_NAME", msg);
            context.startActivity(intent);
        }
    }

    public MyAdapter(String[] mDataset) {
        this.mDataset = mDataset;
    }



    @NonNull
    @Override
    public MyAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view_cell, viewGroup, false);
        MyViewHolder vh = new MyViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder viewHolder, int i) {
        viewHolder.textView.setText(mDataset[i]);

    }

    @Override
    public int getItemCount() {
        return mDataset.length;
    }
}
