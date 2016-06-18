package com.udacity.popularmoviesstage2.network;

import com.udacity.popularmoviesstage2.model.ResponseBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by akhil on 18/06/16.
 */
public interface ApiCalls {
    @GET("http://api.themoviedb.org/3/discover/movie")
    Observable<ResponseBean> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);
}
