package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.model.Movie;

/**
 * Created by akhil on 01/07/16.
 */
public class MovieVM {

    private Movie mMovie;
    private String mName;

    private String mImageUrl;
    private ClickHandler mClickHandler;

    public MovieVM(Movie movie, ClickHandler clickHandler) {
        mName = movie.getOriginalTitle();
        mImageUrl = movie.getPosterPath() != null ? "http://image.tmdb.org/t/p/w342/" + movie.getPosterPath() : null;
        mMovie = movie;
        mClickHandler = clickHandler;
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

    public void onViewClick(View view) {
        mClickHandler.onMovieClicked(mMovie);
    }
}

