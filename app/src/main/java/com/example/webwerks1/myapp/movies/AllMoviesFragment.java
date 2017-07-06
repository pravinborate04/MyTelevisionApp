package com.example.webwerks1.myapp.movies;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.webwerks1.myapp.Main2Activity;
import com.example.webwerks1.myapp.Main4Activity;
import com.example.webwerks1.myapp.OnLoadMoreListener;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.retro.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class AllMoviesFragment extends Fragment implements Callback<ResponseModel>,OnLoadMoreListener {
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
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_all_movies, container, false);
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        moviesRecyclerView=(RecyclerView)view.findViewById(R.id.allmovies);

        retrofit= RetrofitManager.getInstance().getClient();
        service=retrofit.create(AllMoviesService.class);

        resultses=new ArrayList<>();

        linearLayoutManager=new LinearLayoutManager(getActivity());
        gridLayoutManager=new GridLayoutManager(getActivity(),3);

        moviesRecyclerView.setLayoutManager(linearLayoutManager);
        wsMoviesAdapter=new WsMoviesAdapter(getActivity(),resultses,false);
        wsGridAdapter=new WsMoviesAdapter(getActivity(),resultses,true);

        moviesRecyclerView.setAdapter(wsMoviesAdapter);
        loadData(1);
        moviesRecyclerView.addOnScrollListener(new ScrollListener(linearLayoutManager,this));
    }

    private void loadData(int page){
        ((Main4Activity)getActivity()).showProgress();
        service.getAllMovies(RetrofitManager.API_KEY,RetrofitManager.POPULARITY_DESC,"en",""+page)
                .enqueue(this);
    }
    @Override
    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
        if(response.body()!=null && response.body().getResults().size()>0){
            ((Main4Activity)getActivity()).hideProgress();
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
    public void onLoadMore(boolean lastScrlitem) {
        if(lastScrlitem && page<lastPage){
            page++;
            Log.v("AllMoviesActivity",""+page);
            loadData(page);

        }else {
            Toast.makeText(getActivity(),"Reached last Page",Toast.LENGTH_SHORT).show();
        }
    }
}
