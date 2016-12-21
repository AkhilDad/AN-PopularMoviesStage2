package com.udacity.popularmoviesstage2.presenter;


import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.view.MoviesView;
import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;


/**
 * Created by akhil on 18/06/16.
 */
public class MoviesPresenter {

    private final MoviesView mView;
    private final MoviesDataManager mMoviesDataManager;
    private CompositeSubscription mSubscription;

    public MoviesPresenter(MoviesView view, MoviesDataManager moviesDataManager) {
        mView = view;
        mMoviesDataManager = moviesDataManager;
        mSubscription = new CompositeSubscription();
    }

    public void loadPopularMovies(@SortOrder.SortOrderDef String sortOrder) {
        reset();
        mView.clearData();
        mView.showProgress(true);
        mSubscription.add(mMoviesDataManager.getMovies(sortOrder)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<Movie>>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
                mView.showError(e.getMessage());
            }

            @Override
            public void onNext(List<Movie> movies) {
                if (movies != null) {
                    List<MovieVM> movieVMs = new ArrayList<>(movies.size());
                    for (Movie movie : movies) {
                        movieVMs.add(new MovieVM(movie));
                    }
                    mView.updateMovies(movieVMs);
                }
            }
        }));
    }
    public void cleanUp() {
        mMoviesDataManager.cleanUp();
        mSubscription.unsubscribe();
    }

    public void reset() {
        mSubscription.unsubscribe();
        mSubscription = new CompositeSubscription();
    }
}
