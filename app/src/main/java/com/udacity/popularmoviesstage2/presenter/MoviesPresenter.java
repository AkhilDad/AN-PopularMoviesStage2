package com.udacity.popularmoviesstage2.presenter;

import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.view.MoviesView;

/**
 * Created by akhil on 18/06/16.
 */
public class MoviesPresenter {

    public final static String POPULARITY = "popularity.desc";
    public final static String RATING = "vote_average.desc";
    private final MoviesView mView;
    private final MoviesDataManager mMoviesDataManager;

    public MoviesPresenter(MoviesView view, MoviesDataManager moviesDataManager) {
        mView = view;
        mMoviesDataManager = moviesDataManager;
    }

    public void loadPopularMovies() {
        mMoviesDataManager.getMovies(POPULARITY);
    }
}
