package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.ObservableInt;
import android.view.View;

/**
 * Created by akhil on 21/12/16.
 */

public class MovieDetailFragmentVM extends BaseObservable {

    private MovieDetailVM mMovieDetailVM;
    private ObservableInt mMovieReviewsRVVisibility = new ObservableInt(View.GONE);
    private ObservableInt mMovieReviewsProgressVisibility = new ObservableInt(View.GONE);
    private ErrorVM mMovieReviewsErrorVM = new ErrorVM();
    private ObservableInt mMovieTrailersRVVisibility = new ObservableInt(View.GONE);
    private ObservableInt mMovieTrailersProgressVisibility = new ObservableInt(View.GONE);
    private ErrorVM mMovieTrailersErrorVM = new ErrorVM();
//    private List<MovieReviewsVM> mMovieReviewsVMList;
//    private List<MovieTrailersVM> mMovieTrailersVMList;


    public MovieDetailFragmentVM(MovieDetailVM movieDetailVM) {
        mMovieDetailVM = movieDetailVM;
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
}
