package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tacheyourself.mymovie.R;

import java.net.URL;

public class DisplayMovieActivity extends AppCompatActivity {

    private VideoView mVideoView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);
        mVideoView=findViewById(R.id.videoView);
        String url=getIntent().getStringExtra("link");
        Log.d("movie",url);
        mVideoView.setVideoPath(url);
        mVideoView.setMediaController(new MediaController(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mVideoView.start();
    }
}