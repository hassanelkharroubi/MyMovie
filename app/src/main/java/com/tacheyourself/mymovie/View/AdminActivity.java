package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
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

import java.util.ArrayList;

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

        resultAdapter = new ResultAdapter(getBaseContext(),movies);

        results.setAdapter(resultAdapter);

        results.setLayoutManager(new LinearLayoutManager(this));

        Go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RequestQueue requestQueue= Volley.newRequestQueue(getBaseContext());
                StringRequest stringRequest=new StringRequest(Utils.API_SEARCH_URL + movieToSearch.getText().toString(), new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {



                        try {

                            JSONObject QueryResult = new JSONObject(response);
                            JSONArray jsonResultArray= QueryResult.getJSONArray("results");

                            Toast.makeText(AdminActivity.this, jsonResultArray.toString(), Toast.LENGTH_SHORT).show();

                            for (int i=0;i<jsonResultArray.length();i++){
                                JSONObject jsonObject=jsonResultArray.getJSONObject(i);
                                int year = Integer.parseInt(jsonObject.getString("release_date").substring(0,4));
                                String ImageURL = "https://image.tmdb.org/t/p/w500" + jsonObject.getString("poster_path");
                                Log.d("result" + i , jsonObject.toString());
                                movies.add(new Movie(jsonObject.getString("title"),
                                        jsonObject.getString("overview"),jsonObject.getString("original_language"),
                                        ImageURL, year));
                                resultAdapter.notifyDataSetChanged();
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