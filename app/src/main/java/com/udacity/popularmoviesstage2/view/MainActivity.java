package com.udacity.popularmoviesstage2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.presenter.MoviesPresenter;

import java.util.List;

public class MainActivity extends AppCompatActivity implements MoviesView{

    private MoviesPresenter mMoviesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mMoviesPresenter = new MoviesPresenter(this, new MoviesDataManager(getResources()));
        mMoviesPresenter.loadPopularMovies();
    }

    @Override
    public void updateMovies(List<Movie> movieList) {

    }
}
