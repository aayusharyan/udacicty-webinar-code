package com.aayushsinha.android.posts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static String EXTRA_POST_TITLE_KEY = "EXTRA_POST_TITLE_KEY";
    public static String EXTRA_POST_BODY_KEY = "EXTRA_POST_BODY_KEY";
    public static String EXTRA_POST_ID_KEY = "EXTRA_POST_ID_KEY";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final ArrayList<Post> myData = new ArrayList<>();

        RecyclerView postRecyclerView = (RecyclerView) findViewById(R.id.post_recycler_view);
        postRecyclerView.setHasFixedSize(false);


        RecyclerView.LayoutManager layoutManager= new LinearLayoutManager(this);
        postRecyclerView.setLayoutManager(layoutManager);

        final PostListAdapter postListAdapter = new PostListAdapter(myData);
        postRecyclerView.setAdapter(postListAdapter);


        String url = "https://jsonplaceholder.typicode.com/posts";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(url, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                for(int i=0; i< response.length(); i++) {
                    try {
                        JSONObject jsonObject = response.getJSONObject(i);
                        Post post = new Post();
                        post.setId(jsonObject.getInt("id"));
                        post.setBody(jsonObject.getString("body"));
                        post.setTitle(jsonObject.getString("title"));

                        myData.add(post);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                postListAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("VOLLEY_TEST", "ERRORRRRRRRRR!");
            }
        });

        RequestQueue queue = Volley.newRequestQueue(this);
        queue.add(jsonArrayRequest);
    }

}
