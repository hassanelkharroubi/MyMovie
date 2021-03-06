package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tacheyourself.mymovie.Model.Movie;
import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.View.DisplayMovieActivity;
import com.tacheyourself.mymovie.utils.DownloadImageSync;

import java.io.Serializable;

public class DetailMovieActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView movieImageView;
    private TextView mTitleView;
    private TextView mYearView;
    private  TextView mDescriptionView;
    private Button mPlayButton;
    private Movie movie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_detail_movie);

        init();





    }

    private void init(){

        movieImageView=findViewById(R.id.imageView);
        mTitleView=findViewById(R.id.titleMovie);
        mYearView=findViewById(R.id.movieYear);
        mDescriptionView=findViewById(R.id.movieDescription);
        mPlayButton=findViewById(R.id.moviePlay);
        mPlayButton.setOnClickListener(this);
        Intent intent=getIntent();
       movie=(Movie) intent.getSerializableExtra("movie");

        Log.d("detail",movie.getTitle());
        if(movie!=null){
           new DownloadImageSync(movieImageView).execute(movie.getLinkImage());
           Log.d("lien",movie.getLinkMovie());
           mTitleView.setText(movie.getTitle());
           mDescriptionView.setText(movie.getDescription());
           mYearView.setText(movie.getYear()+"");

        }

    }


    @Override
    public void onClick(View view) {



        if(view.getId() ==mPlayButton.getId()) {

            Intent intent=new Intent(this, DisplayMovieActivity.class);
            intent.putExtra("link",movie.getLinkMovie());
            startActivity(intent);

        }

    }
}