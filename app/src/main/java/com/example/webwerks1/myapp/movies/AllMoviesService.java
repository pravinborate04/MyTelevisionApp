package com.example.webwerks1.myapp.movies;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by webwerks1 on 13/5/16.
 */
public interface AllMoviesService {
    @GET("discover/movie")
    Call<ResponseModel> getAllMovies(@Query("api_key") String api_key,
                                     @Query("sort_by") String sort_by,
                                     @Query("language") String language,
                                     @Query("page") String page);

    @GET("search/movie")
    Call<ResponseModel> searchMovies(@Query("api_key") String api_key,
                                     @Query("query") String query,
                                     @Query("page") String page,
                                     @Query("sort_by") String sort_by);
}
