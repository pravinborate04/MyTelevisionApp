package com.example.webwerks1.myapp.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks1.myapp.DataBaseHelper;
import com.example.webwerks1.myapp.R;

import java.util.ArrayList;

public class MovieDetailsActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{
    ImageView imageView;
    TextView txtTitle,txtYear,txtPopularity,txtVoteCount,txtVoteAvg,txtDescptn;
    Results results;
    Switch mFav;
    DataBaseHelper helper;
    ArrayList<Results> resultses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        imageView=(ImageView)findViewById(R.id.imgMoviesDetails);
        txtTitle=(TextView)findViewById(R.id.txtTitle);
        txtYear=(TextView)findViewById(R.id.txtYear);

        resultses=new ArrayList<>();
        txtDescptn=(TextView)findViewById(R.id.txtDescptn);
        mFav=(Switch)findViewById(R.id.switchFav);
        helper=new DataBaseHelper(this);
        results=getIntent().getParcelableExtra("movie");


        mFav.setChecked(helper.isMovieInDatabase(results));

        Log.e("MovieDetail",results.getOriginal_title());
        Glide.with(this).load("http://image.tmdb.org/t/p/w185/"+results.getBackdrop_path()).into(imageView);
        txtTitle.setText(results.getTitle());
        txtYear.setText(results.getRelease_date());
       /* txtPopularity.setText(results.getPopularity()+"");
        txtVoteCount.setText(results.getVote_count());
        txtVoteAvg.setText(results.getVote_average()+"");*/
        txtDescptn.setText(results.getOverview());

        mFav.setOnCheckedChangeListener(this);
        for(Results results:resultses){
            Log.e("AllMoviesFav",results.getOriginal_title());
        }


    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Log.e("OnCheckedChange",""+results.getOriginal_title());
         helper.addMovie(results);
        }
        else {
            Log.e("OnChecked DeleteElse",""+results.getOriginal_title());

            helper.deleteMovie(results.getId()+"");
        }

    }
}
