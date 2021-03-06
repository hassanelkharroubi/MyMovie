package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.tacheyourself.mymovie.Model.Movie;
import com.tacheyourself.mymovie.R;

import java.util.ArrayList;

public class AdminActivity extends AppCompatActivity {

    Button Go;
    EditText movieToSearch;
    RecyclerView results;

    ArrayList<Movie> movies;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        Go = findViewById(R.id.search);
        movieToSearch = findViewById(R.id.MovieNameSearch);
        results = findViewById(R.id.results);

        movies = new ArrayList<Movie>();



    }
}