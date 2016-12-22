package com.udacity.popularmoviesstage2.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.model.Movie;


public class MovieDetailActivity extends AppCompatActivity {
    private static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private Movie mMovie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        initFromIntent(getIntent());
        findViewById(R.id.fl_container);
        replaceFragment(MovieDetailsFragment.newInstance(mMovie));
    }

    private void initFromIntent(Intent intent) {
        mMovie = intent.getParcelableExtra(EXTRA_MOVIE);
    }

    private void replaceFragment(Fragment fragment) {
        final FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.fl_container, fragment).commit();

    }

    public static Intent getCallingIntent(Context context, Movie movie) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(EXTRA_MOVIE, movie);
        return intent;
    }
}
