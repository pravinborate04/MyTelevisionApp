package com.example.webwerks1.myapp.tvseries;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.webwerks1.myapp.OnLoadMoreListener;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.movies.ScrollListener;
import com.example.webwerks1.myapp.movies.AllMoviesService;
import com.example.webwerks1.myapp.retro.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllTvSeriesActivity extends AppCompatActivity implements Callback<TvSeriesResponseModel>,OnLoadMoreListener {
    RecyclerView mTvRecyclerView;
    Retrofit retrofit;
    AllMoviesService service;
    TvSeriesResponseModel model;
    List<TvResults> resultses;
    LinearLayoutManager linearLayoutManager;
    int page=1;
    int lastPage;
    TvService tvService;
    WsTvSeriesAdapter wsTvSeriesAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_tv_series);
        mTvRecyclerView=(RecyclerView)findViewById(R.id.allTvSeries);

        retrofit= RetrofitManager.getInstance().getClient();
        service=retrofit.create(AllMoviesService.class);

        resultses=new ArrayList<>();
        retrofit=RetrofitManager.getInstance().getClient();
        tvService=retrofit.create(TvService.class);
        linearLayoutManager=new LinearLayoutManager(this);
        mTvRecyclerView.setLayoutManager(linearLayoutManager);
        loadData(1);
        wsTvSeriesAdapter=new WsTvSeriesAdapter(this,resultses);

        mTvRecyclerView.setAdapter(wsTvSeriesAdapter);

        mTvRecyclerView.addOnScrollListener(new ScrollListener(linearLayoutManager,this));

    }

    private void loadData(int page){
        tvService.getAllMovies(RetrofitManager.API_KEY,RetrofitManager.POPULARITY_DESC,"en",""+page).enqueue(this);

    }

    @Override
    public void onResponse(Call<TvSeriesResponseModel> call, Response<TvSeriesResponseModel> response) {
        if(response.body()!=null && response.body().getResults().size()>0){
            model=response.body();
            lastPage=response.body().getTotal_pages();
            int sizeBefore,sizeAfter;
            sizeBefore=resultses.size();
            Log.d("size before",resultses.size()+"");
            resultses.addAll(model.getResults());
            sizeAfter=resultses.size();
            Log.d("size after",resultses.size()+"");
            wsTvSeriesAdapter.notifyItemRangeChanged(sizeBefore,sizeAfter);
        }
    }

    @Override
    public void onFailure(Call<TvSeriesResponseModel> call, Throwable t) {

    }

    @Override
    public void onLoadMore(boolean lastScrlitem) {
        if(lastScrlitem){
            page++;
            loadData(page);
        }

    }
}
