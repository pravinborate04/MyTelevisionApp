package com.example.webwerks1.myapp.movies;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.webwerks1.myapp.R;

/**
 * Created by webwerks1 on 13/5/16.
 */
public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MoviesViewHolder> {

    String[] movies;
    LayoutInflater layoutInflater;
    Context mContext;

    public MovieAdapter(Context context, String[] movies){
        mContext=context;
        layoutInflater=LayoutInflater.from(mContext);
        this.movies=movies;
    }

    @Override
    public MoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.movie_single_row,parent,false);
        MoviesViewHolder moviesViewHolder=new MoviesViewHolder(view);
        return moviesViewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesViewHolder holder, int position) {
        holder.txtMovieName.setText(movies[position]);
        Log.e("onBindView",movies[position]);
    }

    @Override
    public int getItemCount() {
        return movies.length;
    }

    class MoviesViewHolder extends RecyclerView.ViewHolder
    {
        TextView txtMovieName;

        public MoviesViewHolder(View itemView) {
            super(itemView);
            txtMovieName=(TextView)itemView.findViewById(R.id.rowMovieName);
        }
    }
}
