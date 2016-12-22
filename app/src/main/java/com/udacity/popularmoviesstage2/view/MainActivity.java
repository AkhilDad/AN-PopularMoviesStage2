package com.udacity.popularmoviesstage2.view;

import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.presenter.MoviesPresenter;

public class MainActivity extends AppCompatActivity implements MoviesFragment.MoviesFragmentCallback{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    public void setActionBarTitle(@StringRes int title) {
        setTitle(title);
    }

    @Override
    public void invalidateOptionsMenu() {
        super.invalidateOptionsMenu();
    }

}
