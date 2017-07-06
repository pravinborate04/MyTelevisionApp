package com.example.webwerks1.myapp.movies;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks1.myapp.R;

import java.util.List;

/**
 * Created by webwerks1 on 13/5/16.
 */
public class WsMoviesAdapter extends RecyclerView.Adapter<WsMoviesAdapter.WsMoviesViewHolder>
{
    Context mContext;
    LayoutInflater layoutInflater;
    List<Results> movies;
    boolean isGrid;
    public WsMoviesAdapter(Context context,List<Results> movies,boolean isGrid){
        this.movies=movies;
        this.mContext=context;
        this.isGrid=isGrid;

        layoutInflater=LayoutInflater.from(mContext);
    }


    @Override
    public WsMoviesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.ws_movies_single_row,parent,false);
        WsMoviesViewHolder viewHolder=new WsMoviesViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(WsMoviesViewHolder holder, int position) {

        if(!isGrid){
            holder.textView.setText(movies.get(position).getOriginal_title());

        }else {
            holder.textView.setVisibility(View.GONE);
        }

        Glide.with(mContext).load("http://image.tmdb.org/t/p/w185/"+movies.get(position).getPoster_path())
                .into(holder.imageView);
        holder.linearLayout.setTag(movies.get(position));

    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    class WsMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;
        public WsMoviesViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wsMovieImage);
            textView=(TextView)itemView.findViewById(R.id.wsMovieName);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linWs);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
           Results results=(Results)linearLayout.getTag();
            Log.e("OnClick",results.getOriginal_title());
            Intent intent=new Intent(mContext, MovieDetailsActivity.class);
            intent.putExtra("movie",results);

            // Get the transition name from the string
            String transitionName = mContext.getString(R.string.transition_string);
            // Define the view that the animation will start from
            View viewStart = v.findViewById(R.id.wsMovieImage);

            ActivityOptionsCompat options =

                    ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
                            viewStart,   // Starting view
                            transitionName    // The String
                    );
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());
        }
    }





   /* private void transitionToActivity(Class target, WsMoviesViewHolder viewHolder, Results sample) {
        final Pair<View, String>[] pairs = TransitionHelper.createSafeTransitionParticipants(activity, false,
                new Pair<>(viewHolder.binding.sampleIcon, activity.getString(R.string.square_blue_name)),
                new Pair<>(viewHolder.binding.sampleName, activity.getString(R.string.sample_blue_title)));
        startActivity(target, pairs, sample);
    }*/
}
