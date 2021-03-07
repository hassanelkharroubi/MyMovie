package com.tacheyourself.mymovie.Model;


import android.content.Context;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tacheyourself.mymovie.R;
import com.tacheyourself.mymovie.utils.Utils;


import java.util.ArrayList;

public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.Holder> {

    Context _context;
    ArrayList<Movie> _movies;

    public ResultAdapter(Context context, ArrayList<Movie> movies){
        _context = context;
        _movies = movies;
    }


    @NonNull
    @Override
    public Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(_context);
        View v = inflater.inflate(R.layout.movie_item,parent,false);
        return new Holder(v);
    }


    @Override
    public void onBindViewHolder(@NonNull Holder holder, int position) {
        holder.movieTitle.setText(_movies.get(position).getTitle());
        holder.releaseDate.setText(Integer.toString(_movies.get(position).getYear()));
        holder.movieImage.setImageBitmap(Utils.getBitmapFromURL(_movies.get(position).getLinkImage()));
    }

    @Override
    public int getItemCount() {
        return _movies.size();
    }



    class Holder extends RecyclerView.ViewHolder {

        TextView releaseDate, movieTitle;
        ImageView movieImage;

        public Holder(@NonNull View itemView) {
            super(itemView);

            releaseDate = itemView.findViewById(R.id.releaseDate);
            movieTitle = itemView.findViewById(R.id.movieTitle);
            movieImage = itemView.findViewById(R.id.movieImage);

        }
    }

}
