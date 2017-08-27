package com.udacity.popularmoviesstage2.viewmodel;

import com.udacity.popularmoviesstage2.model.Movie;

/**
 * Created by akhil on 14/01/17.
 */

public interface FavouriteClickHandler {

    void onFavourite(Movie movie, boolean favourite);
}
