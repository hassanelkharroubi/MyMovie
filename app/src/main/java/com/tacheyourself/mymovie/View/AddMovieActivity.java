package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
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

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;


public class AddMovieActivity extends AppCompatActivity {

    Button addToDB;
    EditText link;
    TextView Title;
    TextView year;
    TextView overview;
    ImageView image;

    Movie movie;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_movie);

        image = findViewById(R.id.movieImage);
        Title = findViewById(R.id.movieTitle);
        year = findViewById(R.id.releaseDate);
        overview = findViewById(R.id.desc);
        link = findViewById(R.id.link);
        addToDB = findViewById(R.id.addToDB);

        movie = (Movie) getIntent().getSerializableExtra("movie");

        Title.setText(movie.getTitle());
        year.setText(Integer.toString(movie.getYear()));
        overview.setText(movie.getDescription());
        new DownloadImageSync(image).execute(movie.getLinkImage());

        addToDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                movie.setLinkMovie(link.getText().toString());
                RequestQueue queue = Volley.newRequestQueue(AddMovieActivity.this);

                String urlInsertion = Utils.URLHOST + "add.php" + movie.getQueryParameters();
                Log.d( "murl" ,urlInsertion);

                StringRequest stringRequest = new StringRequest(Request.Method.GET, urlInsertion, new Response.Listener<String>() {
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
                );
                queue.add(stringRequest);
                Intent intent = new Intent(AddMovieActivity.this,AdminActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });


    }
}