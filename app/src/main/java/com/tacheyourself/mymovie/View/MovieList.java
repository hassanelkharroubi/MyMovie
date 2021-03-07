package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tacheyourself.mymovie.Model.Movie;
import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class MovieList extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;

    private List<Movie> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mMovieList=new ArrayList<>();

        mRecyclerView=findViewById(R.id.recylerView);
        getMovies();

    }



    private List<Movie> getMovies(){

         mRequestQueue = Volley.newRequestQueue(this);

        String url = getUrl("read.php");

        Log.d("movielist",url);


// Request a string response from the provided URL.
        JsonArrayRequest JsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                Log.d("movieList",response.toString());

                JSONObject jsonObject;
                for (int i=0; i<response.length(); i++){
                    try {


                        jsonObject=response.getJSONObject(i);

                        int id= jsonObject.getInt("id");
                        String title=jsonObject.getString("title");

                        String description= jsonObject.getString("description");
                        String language=jsonObject.getString("language");
                        String linkMovie=jsonObject.getString("linkMovie");
                        String linkImage=jsonObject.getString("linkImage");
                        int year=jsonObject.getInt("year");

                        mMovieList.add(new Movie(id,title,description,language,linkMovie,linkImage,year));



                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });



/*
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Log.d("movielist",response);



                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Log.d("movielist",error.getMessage());

            }
        });*/


// Add the request to the RequestQueue.
        mRequestQueue.add(JsonArrayRequest);




        return null;

    }

    private String getUrl(String path){

        return Utils.URLHost+path;

    }

    
}