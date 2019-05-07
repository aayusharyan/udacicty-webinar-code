package com.aayushsinha.android.posts;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        final ArrayList<Comment> data = new ArrayList<>();

        Intent intent = getIntent();
        String post_title = intent.getStringExtra(MainActivity.EXTRA_POST_TITLE_KEY);
        String post_body = intent.getStringExtra(MainActivity.EXTRA_POST_BODY_KEY);
        int post_id = intent.getIntExtra(MainActivity.EXTRA_POST_ID_KEY, 1);

        String url = "https://jsonplaceholder.typicode.com/comments?postId="+post_id;


        ((TextView)findViewById(R.id.detail_post_title)).setText(post_title);
        ((TextView)findViewById(R.id.detail_post_body)).setText(post_body);

        RecyclerView recyclerView = findViewById(R.id.comment_recycler_view);
        recyclerView.setHasFixedSize(false);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        final CommentListAdapter commentListAdapter = new CommentListAdapter(data);
        recyclerView.setAdapter(commentListAdapter);


        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                try {
                    for (int i = 0; i < response.length(); i++) {
                        JSONObject singleComment = response.getJSONObject(i);
                        Comment comment = new Comment();
                        comment.setBody(singleComment.getString("body"));
                        comment.setName(singleComment.getString("name"));
                        comment.setId(singleComment.getInt("id"));
                        data.add(comment);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                commentListAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY_DETAIL", "ERROR");
            }
        });

        RequestQueue q = Volley.newRequestQueue(this);
        q.add(jsonArrayRequest);
    }
}
