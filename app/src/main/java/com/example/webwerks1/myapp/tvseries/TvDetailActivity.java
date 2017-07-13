package com.example.webwerks1.myapp.tvseries;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.webwerks1.myapp.Constants;
import com.example.webwerks1.myapp.DataBaseHelper;
import com.example.webwerks1.myapp.R;
import com.example.webwerks1.myapp.retro.RetrofitManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class TvDetailActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener{

    ImageView imageView;
    TextView txtTitle,txtYear,txtPopularity,txtVoteCount,txtVoteAvg,txtDescptn,txtWebLink;
    TvResults results;
    Switch mFav;
    DataBaseHelper helper;
    ArrayList<TvResults> resultses;
    ListView seasonsListView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tv_detail);
        imageView=(ImageView)findViewById(R.id.imgTvDetails);
        txtTitle=(TextView)findViewById(R.id.txtTvTitle);
        txtYear=(TextView)findViewById(R.id.txtTvYear);
        seasonsListView=(ListView)findViewById(R.id.seasonsListView);
        txtWebLink=(TextView)findViewById(R.id.webLink);

        resultses=new ArrayList<>();
        txtDescptn=(TextView)findViewById(R.id.txtTvDescptn);
        mFav=(Switch)findViewById(R.id.switchTvFav);
        helper=new DataBaseHelper(this);
        results=getIntent().getParcelableExtra("tv");
        mFav.setChecked(helper.isTVInDatabase(results));
        Glide.with(this).load(Constants.POSTER_BASE_URL+results.getBackdrop_path()).into(imageView);
        txtTitle.setText(results.getOriginal_name());
        txtYear.setText(results.getFirst_air_date());
       /* txtPopularity.setText(results.getPopularity()+"");
        txtVoteCount.setText(results.getVote_count());
        txtVoteAvg.setText(results.getVote_average()+"");*/
        txtDescptn.setText(results.getOverview());

        if(isNetworkAvailable()){
            loadDataFromService();
        }
        mFav.setOnCheckedChangeListener(this);
    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        if(isChecked){
            Log.e("OnCheckedChange",""+results.getOriginal_name());
            helper.addTv(results);
        }
        else {
            Log.e("OnChecked DeleteElse",""+results.getOriginal_name());

            helper.deleteTv(results.getId()+"");
        }
    }



    public boolean isNetworkAvailable() {
        boolean isNetwork;

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        isNetwork = activeNetworkInfo != null && activeNetworkInfo.isConnected();

        return isNetwork;
    }



    public void loadDataFromService(){
        Retrofit retrofit;
        retrofit= RetrofitManager.getInstance().getClient();
        TvService tvService;
        tvService=retrofit.create(TvService.class);
        tvService.tvDetails(results.getId()+"",RetrofitManager.API_KEY).enqueue(new Callback<TvDetailsResponseModel>() {
            @Override
            public void onResponse(Call<TvDetailsResponseModel> call, Response<TvDetailsResponseModel> response) {
                if(response.body()!=null){
                    Log.e("resppose",response.isSuccessful()+"");
                    Log.e("res",response.body().getSeasons().size()+"");
                    txtWebLink.setText(response.body().getHomepage());
                    seasonsListView.setAdapter(new SeasonAdapter(TvDetailActivity.this,response.body().getSeasons()));
                }
            }

            @Override
            public void onFailure(Call<TvDetailsResponseModel> call, Throwable t) {

            }
        });

    }
}
