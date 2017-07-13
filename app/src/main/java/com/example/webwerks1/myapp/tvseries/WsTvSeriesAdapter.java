package com.example.webwerks1.myapp.tvseries;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks1.myapp.Constants;
import com.example.webwerks1.myapp.R;

import java.util.List;

/**
 * Created by webwerks1 on 1/6/16.
 */
public class WsTvSeriesAdapter extends RecyclerView.Adapter<WsTvSeriesAdapter.TvViewHolder>{
    Context mContext;
    LayoutInflater layoutInflater;
    List<TvResults> tvResultsList;

    public WsTvSeriesAdapter(Context context,List<TvResults> movies){
        this.tvResultsList=movies;
        this.mContext=context;
        layoutInflater=LayoutInflater.from(mContext);
    }

    @Override
    public TvViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view=layoutInflater.inflate(R.layout.ws_movies_single_row,parent,false);
        TvViewHolder viewHolder=new TvViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TvViewHolder holder, int position) {
       if(tvResultsList.get(position).getOriginal_name()!=null){
           holder.textView.setText(tvResultsList.get(position).getOriginal_name());

       }else {
           holder.textView.setText(tvResultsList.get(position).getName());
       }
        if(tvResultsList.get(position).getPoster_path()!=null){
            Glide.with(mContext).load(Constants.POSTER_BASE_URL+tvResultsList.get(position).getPoster_path())
                    .into(holder.imageView);
        }else {
            holder.imageView.setImageResource(R.mipmap.ic_launcher);
        }

        holder.linearLayout.setTag(tvResultsList.get(position));
    }

    @Override
    public int getItemCount() {
        return tvResultsList.size();
    }

    class TvViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        ImageView imageView;
        TextView textView;
        LinearLayout linearLayout;
        public TvViewHolder(View itemView) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.wsMovieImage);
            textView=(TextView)itemView.findViewById(R.id.wsMovieName);
            linearLayout=(LinearLayout)itemView.findViewById(R.id.linWs);
            linearLayout.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            TvResults results=(TvResults) linearLayout.getTag();
            Intent intent=new Intent(mContext, TvDetailActivity.class);
            intent.putExtra("tv",results);

            View view=v.findViewById(R.id.wsMovieImage);

            // Get the transition name from the string
            String transitionName = mContext.getString(R.string.transition_string);
            ActivityOptionsCompat options =

                    ActivityOptionsCompat.makeSceneTransitionAnimation((Activity) mContext,
                            view,   // Starting view
                            transitionName    // The String
                    );
            ActivityCompat.startActivity((Activity) mContext, intent, options.toBundle());

        }
    }
}
