package com.example.webwerks1.myapp.tvseries;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.Toast;

import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.retro.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TvSearchActivity extends AppCompatActivity implements TextWatcher, Callback<TvSeriesResponseModel> {
    RecyclerView recyclerView;
    EditText editText;
    List<TvResults> searchTvResults;
    CountDownTimer countDownTimer;
    String sr;
    Retrofit retrofit;
    TvService service;
    int page=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_search);
        editText=(EditText)findViewById(R.id.edtTvSearch);
        recyclerView=(RecyclerView)findViewById(R.id.searchRecyclerViewTv);

        retrofit= RetrofitManager.getInstance().getClient();
        service=retrofit.create(TvService.class);

        searchTvResults=new ArrayList<>();
        countDownTimer=new CountDownTimer(1000,500) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                if(!TextUtils.isEmpty(sr)){
                    Toast.makeText(TvSearchActivity.this,""+sr,Toast.LENGTH_SHORT).show();
                    loadData(sr,page);
                }


            }
        };
        editText.addTextChangedListener(this);
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        countDownTimer.cancel();
        recyclerView.setAdapter(null);

    }

    @Override
    public void afterTextChanged(Editable s) {
        sr=s.toString();
        countDownTimer.start();
    }
    public void loadData(String s,int page){
        service.searchTvSeries(RetrofitManager.API_KEY,s,page+"",RetrofitManager.POPULARITY_DESC).enqueue(TvSearchActivity.this);
    }

    @Override
    public void onResponse(Call<TvSeriesResponseModel> call, Response<TvSeriesResponseModel> response) {
        if(response.body()!=null && response.body().getResults().size()>0){
            recyclerView.setLayoutManager(new LinearLayoutManager(this));

            Log.e("Search Before size>>",""+searchTvResults.size());
            searchTvResults.addAll(response.body().getResults());
            Log.e("Search after size>",""+searchTvResults.size());
            recyclerView.setAdapter(new WsTvSeriesAdapter(this,response.body().getResults()));
        }
        else {
            Toast.makeText(TvSearchActivity.this,"No Such Movies Found",Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onFailure(Call<TvSeriesResponseModel> call, Throwable t) {

    }
}
