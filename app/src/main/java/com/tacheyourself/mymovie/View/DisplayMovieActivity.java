package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
import android.widget.ProgressBar;
import android.widget.Toast;
import android.widget.VideoView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.utils.Utils;

import org.json.JSONException;
import org.json.JSONObject;

public class DisplayMovieActivity extends AppCompatActivity {

    private VideoView mVideoView;
    private String mLink;
    private RequestQueue mRequestQueue;
    ProgressBar spinnerView ;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_display_movie);

        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);

        mVideoView=findViewById(R.id.videoView);
        spinnerView= findViewById(R.id.my_spinner);

        final MediaPlayer.OnInfoListener onInfoToPlayStateListener = new MediaPlayer.OnInfoListener() {

            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                if (MediaPlayer.MEDIA_INFO_VIDEO_RENDERING_START == what) {
                    spinnerView.setVisibility(View.GONE);
                }
                if (MediaPlayer.MEDIA_INFO_BUFFERING_START == what) {
                    spinnerView.setVisibility(View.VISIBLE);
                }
                if (MediaPlayer.MEDIA_INFO_BUFFERING_END == what) {
                    //spinnerView.setVisibility(View.VISIBLE);
                }
                return false;
            }
        };
        mVideoView.setOnInfoListener(onInfoToPlayStateListener);

        getSupportActionBar().hide();


       if(getIntent().hasExtra("link")){
           mLink=getIntent().getStringExtra("link");
           startVideo(mLink);

       }

    }

    private void startVideo(String path){
        mVideoView.setVideoPath(path);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();

    }
}