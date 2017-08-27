package com.udacity.popularmoviesstage2.network;

import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.model.ResponseBean;
import com.udacity.popularmoviesstage2.model.Review;
import com.udacity.popularmoviesstage2.model.Trailer;

import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;
import rx.Observable;

import static android.R.attr.id;

/**
 * Created by akhil on 18/06/16.
 */
public interface ApiCalls {

    @GET("discover/movie")
    Observable<ResponseBean<Movie>> getMovies(@Query("api_key") String apiKey, @Query("sort_by") String sortBy);

    @GET("movie/{id}/videos")
    Observable<ResponseBean<Trailer>> getMovieTrailers(@Path("id") long movieId, @Query("api_key") String apiKey);

    @GET("movie/{id}/reviews")
    Observable<ResponseBean<Review>> getMovieReviews(@Path("id") long movieId, @Query("api_key") String apiKey);


}
