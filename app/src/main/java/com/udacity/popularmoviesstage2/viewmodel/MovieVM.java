package com.udacity.popularmoviesstage2.viewmodel;

import com.udacity.popularmoviesstage2.model.Movie;

/**
 * Created by akhil on 01/07/16.
 */
public class MovieVM {

    private Movie mMovie;

    private String name;

    public MovieVM(Movie movie) {
        mMovie = movie;
        name = movie.getOriginalTitle();
    }

    public String getName() {
       return name;
    }
}
