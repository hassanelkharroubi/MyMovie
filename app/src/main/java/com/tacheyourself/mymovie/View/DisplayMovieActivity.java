package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.os.Bundle;
import android.widget.MediaController;
import android.widget.VideoView;

import com.tacheyourself.mymovie.R;

import java.net.URL;

public class DisplayMovieActivity extends AppCompatActivity {

    private VideoView mVideoView;
    public static final String url="https://usa7-cache2.shegu.net/vip/p1/movie_mp4_h264/2021/" +
            "3/0/32530/movie.32530.2021.360p.H264.20210226045859.mp4?KEY1=4dqgLqz7f7y8Ksby_Q-39w&KEY2=1615132569";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_movie);
        mVideoView=findViewById(R.id.videoView);
        mVideoView.setVideoPath(getIntent().getStringExtra("link"));
        mVideoView.setMediaController(new MediaController(this));
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
        mVideoView.start();
    }
}