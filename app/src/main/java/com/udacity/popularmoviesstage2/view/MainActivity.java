package com.udacity.popularmoviesstage2.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.presenter.MoviesPresenter;

public class MainActivity extends AppCompatActivity {

    private MoviesPresenter mMoviesPresenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}
