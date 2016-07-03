package com.udacity.popularmoviesstage2.view;

import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import java.util.List;

/**
 * Created by akhil on 18/06/16.
 */
public interface MoviesView {
    void updateMovies(List<MovieVM> movieVMList);
}
