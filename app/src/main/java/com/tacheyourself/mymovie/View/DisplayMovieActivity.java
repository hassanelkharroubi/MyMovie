package com.tacheyourself.mymovie.View;

import androidx.appcompat.app.AppCompatActivity;

import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;
import android.view.WindowManager;
import android.widget.MediaController;
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
           mLink= Utils.getTrailer(getIntent().getStringExtra("id"));
           getVideo(mLink);
           Log.d("Display",mLink);

       }
       if(getIntent().hasExtra("link")){
           mLink=getIntent().getStringExtra("link");
           startVideo(mLink);


       }




        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);


    }

    private void getVideo(String url){

        mRequestQueue= Volley.newRequestQueue(this);
        JsonObjectRequest jsonObject=new JsonObjectRequest(url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                if(response.has("trailer")){
                    try {
                        Log.d("Display","yes it has");
                        mLink=response.getString("trailer");
                        startVideo(mLink);
                    } catch (JSONException e) {

                        Log.d("Display","error json");
                        Toast.makeText(DisplayMovieActivity.this, "trailer is not found", Toast.LENGTH_SHORT).show();
                        finish();
                        e.printStackTrace();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(DisplayMovieActivity.this, "trailer is not found", Toast.LENGTH_SHORT).show();
                 finish();

            }
        });

        mRequestQueue.add(jsonObject);


    }

    private void startVideo(String path){
        mVideoView.setVideoPath(path);
        mVideoView.setMediaController(new MediaController(this));
        mVideoView.start();

    }
}