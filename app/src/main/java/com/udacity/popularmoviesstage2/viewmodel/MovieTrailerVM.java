package com.udacity.popularmoviesstage2.viewmodel;

import android.view.View;

import com.udacity.popularmoviesstage2.model.Trailer;

/**
 * Created by akhil on 01/01/17.
 */
public class MovieTrailerVM {
    private Trailer mTrailer;
    private TrailerClickHandler mTrailerClickHandler;

    public MovieTrailerVM(Trailer trailer, TrailerClickHandler trailerClickHandler) {
        mTrailer = trailer;
        mTrailerClickHandler = trailerClickHandler;
    }

    public String trailerName() {
        return mTrailer.getTrailerName();
    }

    public String key() {
        return mTrailer.getKey();
    }

    public void onViewTrailer(View view) {
        mTrailerClickHandler.onTrailerClicked(mTrailer);
    }
}
