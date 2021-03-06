package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.tacheyourself.mymovie.Model.Movie;
import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.utils.DownloadImageSync;
import com.tacheyourself.mymovie.utils.Utils;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;


public class AddMovieActivity extends AppCompatActivity {

    Button addToDB,mTrailerBtn;
    EditText link;
    TextView Title;
    TextView year;
    TextView overview;
    ImageView image;

    Movie movie;

    String TrailerURL = "";

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);
        movie = (Movie) getIntent().getSerializableExtra("movie");

        mTrailerBtn=findViewById(R.id.trailer);
        mTrailerBtn.setEnabled(false);

        new Thread(new Trailer()).start();


        image = findViewById(R.id.movieImage);
        Title = findViewById(R.id.movieTitle);
        year = findViewById(R.id.releaseDate);
        overview = findViewById(R.id.desc);
        link = findViewById(R.id.link);
        addToDB = findViewById(R.id.addToDB);


        Title.setText(movie.getTitle());
        year.setText(Integer.toString(movie.getYear()));
        overview.setText(movie.getDescription());
        new DownloadImageSync(image).execute(movie.getLinkImage());

        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                movie.setLinkMovie(link.getText().toString());
                RequestQueue queue = Volley.newRequestQueue(AddMovieActivity.this);

                String urlInsertion = Utils.URLHOST + "add.php";// + movie.getQueryParameters();
                Log.d( "murl" ,urlInsertion);

                StringRequest stringRequest = new StringRequest(Request.Method.POST, urlInsertion, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        Toast.makeText(AddMovieActivity.this, "res : " + response.toString(), Toast.LENGTH_LONG).show();
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(AddMovieActivity.this, "error : " + error.toString(), Toast.LENGTH_LONG).show();
                    }
                }
                ){
                    @Override
                    protected Map<String,String> getParams(){
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("title", movie.getTitle());
                        params.put("description", movie.getDescription());
                        params.put("language", movie.getLanguage());
                        params.put("linkMovie", movie.getLinkMovie());
                        params.put("linkImage", movie.getLinkImage());
                        params.put("year", Integer.toString(movie.getYear()));

                        return params;
                    }

                    @Override
                    public Map<String, String> getHeaders() throws AuthFailureError {
                        Map<String,String> params = new HashMap<String, String>();
                        params.put("Content-Type","application/x-www-form-urlencoded");
                        return params;
                    }

                };
                queue.add(stringRequest);
                Intent intent = new Intent(AddMovieActivity.this,AdminActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        mTrailerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddMovieActivity.this,DisplayMovieActivity.class);
                intent.putExtra("link",TrailerURL);
                startActivity(intent);
            }
        });

    }


    class Trailer implements Runnable{

        @Override
        public void run() {
            RequestQueue requestQueue= Volley.newRequestQueue(getBaseContext());
            String EncodedQuery = "https://api.themoviedb.org/3/movie/"+movie.getId()+"?api_key=" + Utils.API_KEY;

            StringRequest stringRequest=new StringRequest(EncodedQuery, new Response.Listener<String>() {
                @Override
                public void onResponse(String response) {

                    try {
                        JSONObject QueryResult = new JSONObject(response);
                        String ImdbID = QueryResult.getString("imdb_id");
                        movie.setImdbID(ImdbID);

                        {
                            RequestQueue CrawlerRequestQueue= Volley.newRequestQueue(getBaseContext());
                            String Crawler = Utils.getTrailer(ImdbID);
                            Log.d("urli",Crawler);

                            JsonObjectRequest trailerUrlRequest=new JsonObjectRequest(Crawler, null,new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {
                                    if(response.has("trailer")) {
                                        try {
                                            TrailerURL = response.getString("trailer");
                                            Toast.makeText(AddMovieActivity.this, "Trailer has loaded successfully", Toast.LENGTH_SHORT).show();
                                            mTrailerBtn.setEnabled(true);
                                        } catch (JSONException e) {
                                            e.printStackTrace();
                                            mTrailerBtn.setEnabled(false);
                                            Toast.makeText(AddMovieActivity.this, "Trailer not found", Toast.LENGTH_SHORT).show();
                                        }

                                    }
                                    else {
                                        mTrailerBtn.setEnabled(false);
                                        Toast.makeText(AddMovieActivity.this, "Trailer not found", Toast.LENGTH_SHORT).show();
                                    }

                                }
                            }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    mTrailerBtn.setEnabled(false);
                                    Toast.makeText(AddMovieActivity.this, "Trailer not found", Toast.LENGTH_SHORT).show();

                                }
                            });

                            CrawlerRequestQueue.add(trailerUrlRequest);
                        }


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }
            }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {
                    mTrailerBtn.setEnabled(false);
                    Toast.makeText(AddMovieActivity.this, "imdb id : " + error.toString(), Toast.LENGTH_SHORT).show();

                }
            });

            requestQueue.add(stringRequest);
        }
    }
}