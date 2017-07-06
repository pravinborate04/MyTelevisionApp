package com.example.webwerks1.myapp.movies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Slide;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.webwerks1.myapp.BaseActivity;
import com.example.webwerks1.myapp.OnLoadMoreListener;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.retro.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllMoviesActivity extends BaseActivity implements Callback<ResponseModel>,OnLoadMoreListener {

    RecyclerView moviesRecyclerView;
    Retrofit retrofit;
    AllMoviesService service;
    ResponseModel model;
    List<Results> resultses;
    WsMoviesAdapter wsMoviesAdapter;
    WsMoviesAdapter wsGridAdapter;
    private LinearLayoutManager linearLayoutManager;
    private GridLayoutManager gridLayoutManager;
    int page=1;
    int lastPage;


    private void setupWindowAnimations() {
        // Re-enter transition is executed when returning to this activity
        Slide slideTransition = new Slide();
        slideTransition.setSlideEdge(Gravity.LEFT);
        slideTransition.setDuration(500);
        getWindow().setReenterTransition(slideTransition);
        getWindow().setExitTransition(slideTransition);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_movies);
        setupWindowAnimations();
        moviesRecyclerView=(RecyclerView)findViewById(R.id.allmovies);

        retrofit= RetrofitManager.getInstance().getClient();
        service=retrofit.create(AllMoviesService.class);

        resultses=new ArrayList<>();

        linearLayoutManager=new LinearLayoutManager(this);
        gridLayoutManager=new GridLayoutManager(this,3);

        moviesRecyclerView.setLayoutManager(linearLayoutManager);
        wsMoviesAdapter=new WsMoviesAdapter(this,resultses,false);
        wsGridAdapter=new WsMoviesAdapter(this,resultses,true);

        moviesRecyclerView.setAdapter(wsMoviesAdapter);
        loadData(1);
        moviesRecyclerView.addOnScrollListener(new ScrollListener(linearLayoutManager,this));
    }

    private void loadData(int page){
        showProgress();
        service.getAllMovies(RetrofitManager.API_KEY,RetrofitManager.POPULARITY_DESC,"en",""+page)
                .enqueue(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==R.id.idGrid){
            moviesRecyclerView.setLayoutManager(gridLayoutManager);
            moviesRecyclerView.setAdapter(wsGridAdapter);
        }
        else {
            moviesRecyclerView.setLayoutManager(new LinearLayoutManager(this));
            moviesRecyclerView.setAdapter(wsMoviesAdapter);
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onResponse(final Call<ResponseModel> call, Response<ResponseModel> response) {
        if(response.body()!=null && response.body().getResults().size()>0){
            hideProgress();
            model=response.body();
            lastPage=response.body().getTotal_pages();
            int sizeBefore,sizeAfter;
            sizeBefore=resultses.size();
            Log.d("size before",resultses.size()+"");
            resultses.addAll(model.getResults());
            sizeAfter=resultses.size();
            Log.d("size after",resultses.size()+"");
            wsMoviesAdapter.notifyItemRangeChanged(sizeBefore,sizeAfter);
           // ScrollListener.loading=true;

        }

    }

    @Override
    public void onFailure(Call<ResponseModel> call, Throwable t) {

    }

    @Override
    public void onLoadMore(boolean lastScrl) {

        if(lastScrl && page<lastPage){
            page++;
            Log.v("AllMoviesActivity",""+page);
            loadData(page);

        }else {
            Toast.makeText(AllMoviesActivity.this,"Reached last Page",Toast.LENGTH_SHORT).show();
        }

    }
}
