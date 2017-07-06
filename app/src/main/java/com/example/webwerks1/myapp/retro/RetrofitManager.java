package com.example.webwerks1.myapp.retro;

import com.facebook.stetho.okhttp3.StethoInterceptor;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by webwerks on 16/3/16.
 */
public class RetrofitManager {

    private static RetrofitManager mRetrofitManager;
    private static final String BASE_URL = "https://api.themoviedb.org/3/";
    public static final String API_KEY = "8dd64b626447ea589850d8d12d410e79";
    private static Retrofit mRetrofitClient;

    //sort_by
    public static final String POPULARITY_DESC="popularity.desc";
    public static final String VOTE_AVERAGE="vote_average.desc";
    public static final String REVENUE="revenue.desc";

    private RetrofitManager() {
    }


    public static RetrofitManager getInstance() {
        if(mRetrofitManager == null) {
            synchronized (RetrofitManager.class) {

                mRetrofitManager = new RetrofitManager();
                HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
                logging.setLevel(HttpLoggingInterceptor.Level.BODY);

                OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
                httpClient.networkInterceptors().add(new StethoInterceptor());
               // httpClient.addInterceptor(logging);

                mRetrofitClient = new Retrofit.Builder()
                        .baseUrl(BASE_URL)
                        .client(httpClient.build())
                        .addConverterFactory(GsonConverterFactory.create())
                        .build();
            }
        }
        return mRetrofitManager;
    }

    public Retrofit getClient() {
        return mRetrofitClient;
    }
}


