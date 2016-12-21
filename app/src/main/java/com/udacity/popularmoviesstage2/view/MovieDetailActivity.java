package com.udacity.popularmoviesstage2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.model.Movie;


public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private Movie mMovie;

    public static Intent getCallingIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        initFromIntent(getIntent());
    }

    private void initFromIntent(Intent intent) {
        mMovie = (Movie) intent.getParcelableExtra(EXTRA_MOVIE);
    }
}
