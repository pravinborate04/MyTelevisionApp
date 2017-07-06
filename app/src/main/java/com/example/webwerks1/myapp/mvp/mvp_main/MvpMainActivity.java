package com.example.webwerks1.myapp.mvp.mvp_main;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.webwerks1.myapp.BaseActivity;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.movies.AllMoviesActivity;
import com.example.webwerks1.myapp.movies.FavoriteMoviesActivity;
import com.example.webwerks1.myapp.movies.SearchActivity;
import com.example.webwerks1.myapp.tvseries.AllTvSeriesActivity;
import com.example.webwerks1.myapp.tvseries.FavTvSeriesActivity;
import com.example.webwerks1.myapp.tvseries.TvSearchActivity;

public class MvpMainActivity extends BaseActivity implements View.OnClickListener {
    Button btnSearch,btnShowAll,btnFav;
    Button btnShowAllTvSeries,btnTvSearch,btnTvFav;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mvp_main);

        btnSearch=(Button)findViewById(R.id.btnSearch);
        btnShowAll=(Button)findViewById(R.id.btnShowAllMovies);
        btnFav=(Button)findViewById(R.id.btnfav);

        btnShowAllTvSeries=(Button)findViewById(R.id.btnShowAllTv);
        btnTvSearch=(Button)findViewById(R.id.btnTvSearch);
        btnTvFav=(Button)findViewById(R.id.btnTvfav);

        btnSearch.setOnClickListener(this);
        btnShowAll.setOnClickListener(this);
        btnFav.setOnClickListener(this);

        btnShowAllTvSeries.setOnClickListener(this);
        btnTvSearch.setOnClickListener(this);
        btnTvFav.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v==btnSearch){
            startActivity(new Intent(this,SearchActivity.class));

        }else if(v==btnFav){
            startActivity(new Intent(this,FavoriteMoviesActivity.class));
        }
        else if(v==btnShowAllTvSeries){
            startActivity(new Intent(this,AllTvSeriesActivity.class));
        }
        else if(v==btnTvSearch){
            startActivity(new Intent(this,TvSearchActivity.class));
        }
        else if(v==btnTvFav){
            startActivity(new Intent(this, FavTvSeriesActivity.class));
        }
        else{
            startActivity(new Intent(this,AllMoviesActivity.class));
        }
    }
}
