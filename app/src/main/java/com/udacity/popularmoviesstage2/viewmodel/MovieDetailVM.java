package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableField;
import android.util.Log;
import android.view.View;

import com.google.common.primitives.Booleans;
import com.udacity.popularmoviesstage2.BR;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.util.DateAndTimeUtils;

/**
 * Created by akhil on 21/12/16.
 */

public class MovieDetailVM extends BaseObservable {

    private String mName;

    private String mImageUrl;

    private String mReleaseDate;
D
    private String mOverView;

    private Float mRating;

    public ObservableBoolean mIsFavourite;

    private FavouriteClickHandler mFavouriteClickHandler;
    private Movie mMovie;

    public MovieDetailVM(Movie movie, FavouriteClickHandler favouriteClickHandler) {
        mMovie = movie;
        mName = movie.getOriginalTitle();
        mImageUrl = movie.getPosterPath() != null ? "http://image.tmdb.org/t/p/w342/" + movie.getPosterPath() : null;
        mOverView = movie.getOverview() != null ? movie.getOverview() : "";
        mRating = movie.getVoteAverage();
        mReleaseDate = DateAndTimeUtils.getDateString(movie.getReleaseDate());
        mFavouriteClickHandler = favouriteClickHandler;
        mIsFavourite = new ObservableBoolean(false);
    }


    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }


    public String getImageUrl() {
        return mImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        mImageUrl = imageUrl;
    }


    public String getReleaseDate() {
        return mReleaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        mReleaseDate = releaseDate;
    }


    public String getOverView() {
        return mOverView;
    }

    public void setOverView(String overView) {
        mOverView = overView;
    }


    public Float getRating() {
        return mRating;
    }

    public void setRating(Float rating) {
        mRating = rating;
    }

    @Bindable
    public boolean isFavourite() {
        return mIsFavourite.get();
    }

    public void setFavourite(boolean favourite) {
        mIsFavourite.set(favourite);
    }

    public void onFavourite(View view) {
        setFavourite(!isFavourite());
        mFavouriteClickHandler.onFavourite(mMovie, isFavourite());
        Log.e("On favourite clicked","favourite");
    }
}