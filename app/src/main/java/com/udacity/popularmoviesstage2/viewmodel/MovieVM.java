package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.model.Movie;

/**
 * Created by akhil on 01/07/16.
 */
public class MovieVM {

    private String mName;

    private String mImageUrl;

    public MovieVM(Movie movie) {
        mName = movie.getOriginalTitle();
        mImageUrl = movie.getPosterPath() != null ? "http://image.tmdb.org/t/p/w342/" + movie.getPosterPath() : null;
    }

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_image)
                .into(view);
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
}

