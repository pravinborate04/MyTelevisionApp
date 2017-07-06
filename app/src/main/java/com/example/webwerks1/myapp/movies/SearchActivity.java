
package com.example.webwerks1.myapp.movies;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.AppCompatEditText;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.Toast;

import com.example.webwerks1.myapp.BaseActivity;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.retro.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class SearchActivity extends BaseActivity implements TextWatcher, Callback<ResponseModel> {
    RecyclerView searchRecyclerView;
    AppCompatEditText edtSearch;
    CountDownTimer countDownTimer;
    String sr;
    Retrofit retrofit;
    AllMoviesService service;
    List<Results> searchResults;
    WsMoviesAdapter wsMoviesAdapter;
    int page=1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        searchRecyclerView=(RecyclerView)findViewById(R.id.searchRecyclerView);
        edtSearch=(AppCompatEditText)findViewById(R.id.edtSearch);
        retrofit= RetrofitManager.getInstance().getClient();
        service=retrofit.create(AllMoviesService.class);
        searchResults=new ArrayList<>();
        countDownTimer=new CountDownTimer(1000,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(!TextUtils.isEmpty(sr)){
                    loadData(sr,page);
                }


            }
        };
        edtSearch.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after)
    {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count)
    {
        countDownTimer.cancel();
        searchRecyclerView.setAdapter(null);
    }

    @Override
    public void afterTextChanged(Editable s)
    {
        sr=s.toString();
        countDownTimer.start();

    }

    @Override
    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
        if(response.body()!=null && response.body().getResults().size()>0){
            hideProgress();
            searchRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            Log.e("Search Before size>>",""+searchResults.size());
            searchResults.addAll(response.body().getResults());
            Log.e("Search after size>",""+searchResults.size());
            searchRecyclerView.setAdapter(new WsMoviesAdapter(this,response.body().getResults(),false));
        }
        else {
            hideProgress();
            Toast.makeText(SearchActivity.this,"No Such Movies Found",Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onFailure(Call<ResponseModel> call, Throwable t) {

    }

    public void loadData(String s,int page){
        showProgress();
        service.searchMovies(RetrofitManager.API_KEY,s,page+"",RetrofitManager.POPULARITY_DESC).enqueue(SearchActivity.this);
    }
}
