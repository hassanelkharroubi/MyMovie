package com.tacheyourself.mymovie.utils;
/*
 **    *** MyMovie ***
 **   Created by EL KHARROUBI HASSAN
 **   At Sunday March 2021 00H 23MIN
 */


import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class Utils {

    public static final String API_KEY = "066896fd01b3125f675c785d1ea214c7";
    public static final String API_SEARCH_URL = "https://api.themoviedb.org/3/search/movie?api_key=" + API_KEY +"&query=";

    public static Bitmap getBitmapFromURL(String src) {
        try {
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception",e.getMessage());
            return null;
        }
    }
    
}
