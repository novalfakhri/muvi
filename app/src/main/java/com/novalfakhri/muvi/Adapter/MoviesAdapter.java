package com.novalfakhri.muvi.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.novalfakhri.muvi.DetailActivity;
import com.novalfakhri.muvi.Model.Movies;
import com.novalfakhri.muvi.R;

import java.util.List;

/**
 * Created by Toshiba on 8/22/2017.
 */

public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MyHolder> {

    private List<Movies> moviesList;
    private int rowLayout;
    private Context context;


    public class MyHolder extends RecyclerView.ViewHolder {
        TextView rating;
        ImageView poster;

        public MyHolder(View v) {
            super(v);
            rating = (TextView) v.findViewById(R.id.item_rating);
            poster = (ImageView) v.findViewById(R.id.item_poster);

            poster.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    Intent i = new Intent(poster.getContext(), DetailActivity.class);
                    i.putExtra("id", moviesList.get(position).getId());
                    i.putExtra("title", moviesList.get(position).getTitle());
                    i.putExtra("release", moviesList.get(position).getReleaseDate());
                    i.putExtra("rating", moviesList.get(position).getVoteAverage().toString());
                    i.putExtra("plot", moviesList.get(position).getOverview());
                    i.putExtra("backdrop", moviesList.get(position).getBackdropPath());
                    poster.getContext().startActivity(i);
                }
            });
        }
    }

    public MoviesAdapter(List<Movies> movies, int rowLayout, Context context) {
        this.moviesList = movies;
        this.rowLayout = rowLayout;
        this.context = context;
    }

    @Override
    public MoviesAdapter.MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(rowLayout, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        Movies movies = moviesList.get(position);
        holder.rating.setText(movies.getVoteAverage().toString());
        holder.poster.setImageResource(R.mipmap.ic_launcher);
        Glide.with(context)
                .load("https://image.tmdb.org/t/p/w500" + movies.getPosterPath())
                .into(holder.poster);
    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }

}
