package com.udacity.popularmoviesstage2.data;

import android.content.Context;
import android.content.res.Resources;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.model.ResponseBean;
import com.udacity.popularmoviesstage2.model.Review;
import com.udacity.popularmoviesstage2.model.Trailer;
import com.udacity.popularmoviesstage2.network.ApiCalls;
import com.udacity.popularmoviesstage2.network.RetrofitSingleton;

import java.util.List;

import retrofit2.Retrofit;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Created by akhil on 28/12/16.
 */

public class MovieDetailsDataManager {
    private Resources mResources;

    public MovieDetailsDataManager(Context context) {
        mResources = context.getResources();
    }

    public Observable<List<Trailer>> getTrailers(long movieId) {
        final Retrofit retrofit = RetrofitSingleton.getRetrofit(RetrofitSingleton.MOVIE_DB_BASE_URL);
        final ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        return apiCalls.getMovieTrailers(movieId, mResources.getString(R.string.api_key)).flatMap(new Func1<ResponseBean<Trailer>, Observable<List<Trailer>>>() {
            @Override
            public Observable<List<Trailer>> call(final ResponseBean<Trailer> responseBean) {
                return Observable.create(new Observable.OnSubscribe<List<Trailer>>() {

                    @Override
                    public void call(Subscriber<? super List<Trailer>> subscriber) {
                        final List<Trailer> reviews = responseBean.getResults();
                        subscriber.onNext(reviews);
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }



    public Observable<List<Review>> getReviews(long movieId) {
        final Retrofit retrofit = RetrofitSingleton.getRetrofit(RetrofitSingleton.MOVIE_DB_BASE_URL);
        final ApiCalls apiCalls = retrofit.create(ApiCalls.class);
        return apiCalls.getMovieReviews(movieId, mResources.getString(R.string.api_key))
                .flatMap(new Func1<ResponseBean<Review>, Observable<List<Review>>>() {
            @Override
            public Observable<List<Review>> call(final ResponseBean<Review> responseBean) {
                return Observable.create(new Observable.OnSubscribe<List<Review>>() {

                    @Override
                    public void call(Subscriber<? super List<Review>> subscriber) {
                        final List<Review> reviews = responseBean.getResults();
                        subscriber.onNext(reviews);
                        subscriber.onCompleted();
                    }
                });
            }
        });
    }

    public void cleanUp() {
        mResources = null;
    }

    public void updateFavourite(Movie movie, boolean favourite) {

    }
}
