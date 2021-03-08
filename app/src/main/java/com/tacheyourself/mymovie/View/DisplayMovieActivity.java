package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tacheyourself.mymovie.R;

import java.net.URL;

public class DisplayMovieActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private String mLink;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_movie);

        mVideoView=findViewById(R.id.videoView);
        getSupportActionBar().hide();

        String url=getIntent().getStringExtra("link");
       if(getIntent().hasExtra("id")){
           mLink=getIntent().getStringExtra("id");

       }
       if(getIntent().hasExtra("link")){
           mLink=getIntent().getStringExtra("link");

       }
        mVideoView.setVideoPath(mLink);
        mVideoView.setMediaController(new MediaController(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


        mVideoView.start();
    }
}