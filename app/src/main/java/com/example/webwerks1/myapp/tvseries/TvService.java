package com.example.webwerks1.myapp.tvseries;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

/**
 * Created by webwerks1 on 1/6/16.
 */
public interface TvService
{
    @GET("discover/tv")
    Call<TvSeriesResponseModel> getAllMovies(@Query("api_key") String api_key,
                                             @Query("sort_by") String sort_by,
                                             @Query("language") String language,
                                             @Query("page") String page);

    @GET("search/tv")
    Call<TvSeriesResponseModel> searchTvSeries(@Query("api_key") String api_key,
                                               @Query("query") String query,
                                               @Query("page") String page,
                                               @Query("sort_by") String sort_by);


    @GET("tv/{id}")
    Call<TvDetailsResponseModel> tvDetails(@Path("id") String id,
                                           @Query("api_key") String api_key);
}
