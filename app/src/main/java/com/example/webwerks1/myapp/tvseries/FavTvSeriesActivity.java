package com.example.webwerks1.myapp.tvseries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.webwerks1.myapp.DataBaseHelper;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.movies.Results;
import com.example.webwerks1.myapp.movies.WsMoviesAdapter;

import java.util.ArrayList;

public class FavTvSeriesActivity extends AppCompatActivity {

    RecyclerView favRecyclerView;
    ArrayList<TvResults> resultsArrayList;
    DataBaseHelper helper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fav_tv_series);

        favRecyclerView=(RecyclerView)findViewById(R.id.recyclrFavTv);
        resultsArrayList=new ArrayList<>();
        helper=new DataBaseHelper(this);
        resultsArrayList=helper.getAllTV();
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favRecyclerView.setAdapter(new WsTvSeriesAdapter(this,resultsArrayList));
    }


}
