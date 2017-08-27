package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableInt;
import android.view.View;

import java.util.List;

/**
 * Created by akhil on 21/12/16.
 */

public class MovieDetailFragmentVM extends BaseObservable {

    private MovieDetailVM mMovieDetailVM;
    private ObservableInt mMovieReviewsRVVisibility = new ObservableInt(View.GONE);
    private ObservableInt mMovieReviewsProgressVisibility = new ObservableInt(View.GONE);
    private ErrorVM mMovieReviewsErrorVM;
    private ObservableInt mMovieTrailersRVVisibility = new ObservableInt(View.GONE);
    private ObservableInt mMovieTrailersProgressVisibility = new ObservableInt(View.GONE);
    private ErrorVM mMovieTrailersErrorVM;
    private List<MovieReviewVM> mMovieReviewVMList;
    private List<MovieTrailerVM> mMovieTrailerVMList;

    public MovieDetailFragmentVM(MovieDetailVM movieDetailVM, ErrorClickHandler trailerErrorClickHandler, ErrorClickHandler reviewsErrorClickHandler) {
        mMovieDetailVM = movieDetailVM;
        mMovieReviewsErrorVM = new ErrorVM(reviewsErrorClickHandler);
        mMovieTrailersErrorVM = new ErrorVM(trailerErrorClickHandler);
    }

    @Bindable
    public MovieDetailVM getMovieDetailVM() {
        return mMovieDetailVM;
    }

    public void setMovieDetailVM(MovieDetailVM movieDetailVM) {
        mMovieDetailVM = movieDetailVM;
    }

    @Bindable
    public ObservableInt getMovieReviewsRVVisibility() {
        return mMovieReviewsRVVisibility;
    }

    public void setMovieReviewsRVVisibility(int movieReviewsRVVisibility) {
        mMovieReviewsRVVisibility.set(movieReviewsRVVisibility);
    }

    @Bindable
    public ObservableInt getMovieReviewsProgressVisibility() {
        return mMovieReviewsProgressVisibility;
    }

    public void setMovieReviewsProgressVisibility(int movieReviewsProgressVisibility) {
        mMovieReviewsProgressVisibility.set(movieReviewsProgressVisibility);
    }

    public ErrorVM getMovieReviewsErrorVM() {
        return mMovieReviewsErrorVM;
    }

    @Bindable
    public void setMovieReviewsErrorVM(ErrorVM movieReviewsErrorVM) {
        mMovieReviewsErrorVM = movieReviewsErrorVM;
    }

    @Bindable
    public ObservableInt getMovieTrailersRVVisibility() {
        return mMovieTrailersRVVisibility;
    }

    public void setMovieTrailersRVVisibility(int movieTrailersRVVisibility) {
        mMovieTrailersRVVisibility.set(movieTrailersRVVisibility);
    }

    @Bindable
    public ObservableInt getMovieTrailersProgressVisibility() {
        return mMovieTrailersProgressVisibility;
    }

    public void setMovieTrailersProgressVisibility(int movieTrailersProgressVisibility) {
        mMovieTrailersProgressVisibility.set(movieTrailersProgressVisibility);
    }

    @Bindable
    public ErrorVM getMovieTrailersErrorVM() {
        return mMovieTrailersErrorVM;
    }

    public void setMovieTrailersErrorVM(ErrorVM movieTrailersErrorVM) {
        mMovieTrailersErrorVM = movieTrailersErrorVM;
    }

    public List<MovieTrailerVM> getMovieTrailerVMList() {
        return mMovieTrailerVMList;
    }

    public void setMovieTrailerVMList(List<MovieTrailerVM> movieTrailerVMList) {
        mMovieTrailerVMList = movieTrailerVMList;
    }

    public List<MovieReviewVM> getMovieReviewVMList() {
        return mMovieReviewVMList;
    }

    public void setMovieReviewVMList(List<MovieReviewVM> movieReviewVMList) {
        mMovieReviewVMList = movieReviewVMList;
    }
}
