package com.example.webwerks1.myapp.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.webwerks1.myapp.DataBaseHelper;
import com.example.webwerks1.myapp.R;

import java.util.ArrayList;

public class FavoriteMoviesActivity extends AppCompatActivity {

    RecyclerView favRecyclerView;
    ArrayList<Results> resultsArrayList;
    DataBaseHelper helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite_movies);
        favRecyclerView=(RecyclerView)findViewById(R.id.recyclrFavMovie);
        resultsArrayList=new ArrayList<>();
        helper=new DataBaseHelper(this);
        resultsArrayList=helper.getAllMovies();
        favRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        favRecyclerView.setAdapter(new WsMoviesAdapter(this,resultsArrayList,false));

    }
}
