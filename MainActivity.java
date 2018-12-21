package com.example.cc.recyclervollypicasso;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements ExampleAdapter.OnItemClickListioner {

    public static final String EXTRA_URL = "imageUrl";   //These are the String keys we pass in the intent
    public static final String EXTRA_CREATER = "creatorName";
    public static final String EXTRA_LIKES = "LikeCount";


    private RecyclerView recyclerView;
    private ExampleAdapter exampleAdapter;
    private ArrayList<ExampleItems> mExampleList;
    private RequestQueue mReqquest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recycler_view);

        recyclerView.setHasFixedSize(true); //Our recyclerview does not change it we

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mExampleList = new ArrayList<>();
        mReqquest = Volley.newRequestQueue(this);   //Request bnane ke liye
        parseJSON();

    }

    private void parseJSON() {
        String url = "https://pixabay.com/api/?key=10935089-f2c3a30bb61ac2df1d1713464&q=yellow+flowers&image_type=photo&pretty=true";


        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,       // Ager json sara Array me hota to jsonArrayRequest lete
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("hits");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject hit = jsonArray.getJSONObject(i);
                                String user = hit.getString("user");
                                int likes = hit.getInt("likes");
                                String imageurl = hit.getString("webformatURL"); // We use those values in mExampleList


                                mExampleList.add(new ExampleItems(imageurl, user, likes)); //We set a constructor in Exampleitems class so we pass parameter

                            }

                            exampleAdapter = new ExampleAdapter(MainActivity.this, mExampleList);   //adater me pass ker diya

                            recyclerView.setAdapter(exampleAdapter);  //set ker dena he adapter me
                            exampleAdapter.setOnItemClickListioner(MainActivity.this);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        mReqquest.add(request);  //add the request
    }

    @Override
    public void onitemClick(int position) {
        Intent detailIntent = new Intent(this, DetailActivity.class);
        ExampleItems clickedItem = mExampleList.get(position); // We get the clicked items in the mExampleList


        detailIntent.putExtra(EXTRA_URL, clickedItem.getmImageUrl());
        detailIntent.putExtra(EXTRA_CREATER,clickedItem.getmCreator());
        detailIntent.putExtra(EXTRA_LIKES,clickedItem.getmLikes());

        startActivity(detailIntent);


    }
}
//We pass json data into our mExampleList and pass into exampleAdapter