package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
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
import com.tacheyourself.mymovie.Model.ResultAdapter;
import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.text.BreakIterator;
import java.util.ArrayList;
import java.util.List;

public class MovieListActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RequestQueue mRequestQueue;
    private ResultAdapter mResultAdapter;

    private List<Movie> mMovieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);
        mMovieList=new ArrayList<>();


        mRecyclerView=findViewById(R.id.recylerView);
        mMovieList=new ArrayList<>();
        mMovieList=getMovies();
        Log.d("hhh",mMovieList.size()+"");
        mResultAdapter=new ResultAdapter(this, (ArrayList<Movie>) mMovieList);

        mRecyclerView.setLayoutManager(new GridLayoutManager(this,2));
        mRecyclerView.setAdapter(mResultAdapter);







    }



    private List<Movie> getMovies(){

         mRequestQueue = Volley.newRequestQueue(this);

        String url = getUrl("read.php");




// Request a string response from the provided URL.
        JsonArrayRequest JsonArrayRequest=new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject;
                for (int i=0; i<response.length(); i++){
                    try {


                        jsonObject=response.getJSONObject(i);

                        int id= jsonObject.getInt("id");
                        String title=jsonObject.getString("title");
                        Log.d("movie",title);

                        String description= jsonObject.getString("description");
                        Log.d("movie",description);
                        String language=jsonObject.getString("language");
                        Log.d("movie",language);
                        String linkMovie=jsonObject.getString("linkMovie");
                        Log.d("movie",linkMovie);
                        String linkImage=jsonObject.getString("linkImage");
                        Log.d("movie",linkImage);
                        int year=jsonObject.getInt("year");
                        Log.d("movie",year+"");


                            mMovieList.add(new Movie(id,title,description,language,linkMovie,linkImage,year));






                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
                mResultAdapter.notifyDataSetChanged();



            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        mRequestQueue.add(JsonArrayRequest);




        return mMovieList;

    }

    private String getUrl(String path){

        return Utils.URLHOST+path;

    }

    
}