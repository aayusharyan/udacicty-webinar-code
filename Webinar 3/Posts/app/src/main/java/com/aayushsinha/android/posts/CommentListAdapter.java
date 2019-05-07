package com.aayushsinha.android.posts;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class CommentListAdapter extends RecyclerView.Adapter<CommentListAdapter.CommentViewHolder> {

    public ArrayList<Comment> commentData;

    public static class CommentViewHolder extends RecyclerView.ViewHolder {

        public TextView comment_name;
        public TextView comment_body;

        public CommentViewHolder(@NonNull View itemView) {
            super(itemView);
            comment_name = itemView.findViewById(R.id.comment_name);
            comment_body = itemView.findViewById(R.id.comment_body);
        }
    }

    public CommentListAdapter(ArrayList<Comment> commentData) {
        this.commentData = commentData;
    }

    @NonNull
    @Override
    public CommentViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.comment_list_cell, viewGroup, false);
        CommentViewHolder commentViewHolder = new CommentViewHolder(v);
        return commentViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull CommentViewHolder commentViewHolder, int i) {
        commentViewHolder.comment_body.setText(commentData.get(i).getBody());
        commentViewHolder.comment_name.setText(commentData.get(i).getName());
    }

    @Override
    public int getItemCount() {
        return commentData.size();
    }
}
