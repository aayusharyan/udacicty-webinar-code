package com.aayushsinha.android.posts;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class PostListAdapter extends RecyclerView.Adapter<PostListAdapter.PostListViewHolder> {

    private ArrayList<Post> postData;

    public static class PostListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public TextView post_title;
        public TextView post_body;
        public int post_id;
        public Context context;

        public PostListViewHolder(@NonNull View itemView) {
            super(itemView);
            post_title = (TextView) itemView.findViewById(R.id.post_title);
            post_body = (TextView) itemView.findViewById(R.id.post_body);
            context = itemView.getContext();
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            final Intent intent;
            intent = new Intent(context, DetailActivity.class);
            String post_title = ((TextView)v.findViewById(R.id.post_title)).getText().toString();
            String post_body = ((TextView)v.findViewById(R.id.post_body)).getText().toString();
            intent.putExtra(MainActivity.EXTRA_POST_TITLE_KEY, post_title);
            intent.putExtra(MainActivity.EXTRA_POST_BODY_KEY, post_body);
            intent.putExtra(MainActivity.EXTRA_POST_ID_KEY, post_id);

            context.startActivity(intent);
        }
    }

    public PostListAdapter(ArrayList<Post> postData) {
        this.postData = postData;
    }

    @NonNull
    @Override
    public PostListViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.post_list_cell, viewGroup, false);
        PostListViewHolder vh = new PostListViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull PostListViewHolder postListViewHolder, int i) {
        postListViewHolder.post_body.setText(postData.get(i).getBody());
        postListViewHolder.post_title.setText(postData.get(i).getTitle());
        postListViewHolder.post_id = postData.get(i).getId();
    }


    @Override
    public int getItemCount() {
        return postData.size();
    }
}
