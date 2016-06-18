package com.udacity.popularmoviesstage2.data;

import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.model.ResponseBean;
import com.udacity.popularmoviesstage2.network.ApiCalls;
import com.udacity.popularmoviesstage2.network.RetrofitSingleton;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by akhil on 18/06/16.
 */
public class MoviesDataManager {

    public MoviesDataManager() {
    }

    public Observable<List<Movie>> getMovies(String sortBy) {
        final Retrofit retrofit = RetrofitSingleton.getRetrofit(RetrofitSingleton.MOVIE_DB_BASE_URL);
        final ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        return apiCalls.getMovies("", sortBy).flatMap(new Func1<ResponseBean, Observable<List<Movie>>>() {

            @Override
            public Observable<List<Movie>> call(final ResponseBean responseBean) {
               return Observable.create(new Observable.OnSubscribe<List<Movie>>() {
                    @Override
                    public void call(Subscriber<? super List<Movie>> subscriber) {
                        final List<Movie> movies = responseBean.getMovies();
                        subscriber.onNext(movies);
                    }
                });
            }
        });
    }
}
