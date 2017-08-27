package com.udacity.popularmoviesstage2.view;

import com.udacity.popularmoviesstage2.model.Review;
import com.udacity.popularmoviesstage2.model.Trailer;
import com.udacity.popularmoviesstage2.viewmodel.MovieReviewVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieTrailerVM;

import java.util.List;

/**
 * Created by akhil on 28/12/16.
 */
public interface MoviesDetailsView {
    void updateTrailers(List<MovieTrailerVM> movieTrailerVMList);

    void updateReviews(List<MovieReviewVM> movieReviewVMList);

    void showError(String message);

    void updateReviewsViewState(int viewState);

    void updateTrailersViewState(int viewState);

    void clearData();

    void openTrailerLink(Trailer trailer);

    void openReviewLink(Review review);
}