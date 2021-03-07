package com.tacheyourself.mymovie.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tacheyourself.mymovie.Model.Movie;
import com.tacheyourself.mymovie.Model.ResultAdapter;
import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Base64;

public class AdminActivity extends AppCompatActivity {

    Button Go;
    EditText movieToSearch;
    RecyclerView results;

    ArrayList<Movie> movies;

    ResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Go = findViewById(R.id.search);
        movieToSearch = findViewById(R.id.MovieNameSearch);
        results = findViewById(R.id.results);

        movies = new ArrayList<Movie>();

        resultAdapter = new ResultAdapter(AdminActivity.this,movies);

        results.setAdapter(resultAdapter);

        results.setLayoutManager(new GridLayoutManager(this,2));

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!movies.isEmpty()) movies.clear();
                RequestQueue requestQueue= Volley.newRequestQueue(getBaseContext());
                String EncodedQuery = "";
                try {
                    EncodedQuery = URLEncoder.encode(movieToSearch.getText().toString(),"utf-8");
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }
                StringRequest stringRequest=new StringRequest(Utils.API_SEARCH_URL + EncodedQuery, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try {

                            JSONObject QueryResult = new JSONObject(response);
                            JSONArray jsonResultArray = QueryResult.getJSONArray("results");

                            if (jsonResultArray.length()>0){
                                for (int i = 0; i < jsonResultArray.length(); i++) {
                                    JSONObject jsonObject = jsonResultArray.getJSONObject(i);
                                    String yearString = jsonObject.getString("release_date");
                                    int year = 0;
                                    if(!yearString.equals(""))
                                    year = Integer.parseInt(jsonObject.getString("release_date").substring(0, 4));
                                    Log.d("movie", i + " : " + year);
                                    String ImageURL = "https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path");
                                    //Log.d("result" + i, jsonObject.toString());
                                    Movie movie = new Movie(jsonObject.getString("title"),
                                            jsonObject.getString("overview"), jsonObject.getString("original_language"),
                                            ImageURL, year);
                                    movies.add(movie);
                                    resultAdapter.notifyDataSetChanged();
                                }
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText(AdminActivity.this, error.toString(), Toast.LENGTH_SHORT).show();

                    }
                });

                requestQueue.add(stringRequest);
            }
        });



    }
}