package com.udacity.popularmoviesstage2.presenter;

import com.udacity.popularmoviesstage2.data.MovieDetailsDataManager;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.model.Review;
import com.udacity.popularmoviesstage2.model.Trailer;
import com.udacity.popularmoviesstage2.view.MoviesDetailsView;
import com.udacity.popularmoviesstage2.view.MoviesFragment;
import com.udacity.popularmoviesstage2.viewmodel.MovieReviewVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieTrailerVM;
import com.udacity.popularmoviesstage2.viewmodel.ReviewClickHandler;
import com.udacity.popularmoviesstage2.viewmodel.TrailerClickHandler;

import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by akhil on 28/12/16.
 */

public class MovieDetailsPresenter {

    private final MoviesDetailsView mView;
    private final MovieDetailsDataManager mMovieDetailsDataManager;
    private CompositeSubscription mSubscription;
    private ReviewClickHandler mReviewClickHandler;
    private TrailerClickHandler mTrailerClickHandler;
    public MovieDetailsPresenter(MoviesDetailsView view, MovieDetailsDataManager movieDetailsDataManager) {
        mView = view;
        mMovieDetailsDataManager = movieDetailsDataManager;
        mSubscription = new CompositeSubscription();
        mTrailerClickHandler = new TrailerClickHandler() {
            @Override
            public void onTrailerClicked(Trailer trailer) {
                mView.openTrailerLink(trailer);
            }
        };
        mReviewClickHandler = new ReviewClickHandler() {
            @Override
            public void onReviewClicked(Review review) {
                mView.openReviewLink(review);
            }
        };
    }

    public void loadReviews(long movieId) {
        mView.updateReviewsViewState(MoviesFragment.LOADING);
        mSubscription.add(mMovieDetailsDataManager.getReviews(movieId)
                .map(new Func1<List<Review>, List<MovieReviewVM>>() {
                    @Override
                    public List<MovieReviewVM> call(List<Review> reviewList) {
                        if (reviewList != null) {
                            List<MovieReviewVM> movieReviewVMs = new ArrayList<>(reviewList.size());
                            for (Review review : reviewList) {
                                movieReviewVMs.add(new MovieReviewVM(review, mReviewClickHandler));
                            }
                            return movieReviewVMs;
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MovieReviewVM>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.updateReviewsViewState(MoviesFragment.ERROR);
                    }

                    @Override
                    public void onNext(List<MovieReviewVM> reviewVMList) {
                        mView.updateReviews(reviewVMList);
                        mView.updateReviewsViewState(MoviesFragment.DATA_LOADED);

                    }
                }));
    }

    public void loadTrailers(long movieId) {
        mView.updateTrailersViewState(MoviesFragment.LOADING);
        mSubscription.add(mMovieDetailsDataManager.getTrailers(movieId)
                .map(new Func1<List<Trailer>, List<MovieTrailerVM>>() {
                    @Override
                    public List<MovieTrailerVM> call(List<Trailer> trailerList) {
                        if (trailerList != null) {
                            List<MovieTrailerVM> movieTrailerVMs = new ArrayList<>(trailerList.size());
                            for (Trailer trailer : trailerList) {
                                movieTrailerVMs.add(new MovieTrailerVM(trailer, mTrailerClickHandler));
                            }
                            return movieTrailerVMs;
                        }
                        return null;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<List<MovieTrailerVM>>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        mView.showError(e.getMessage());
                        mView.updateReviewsViewState(MoviesFragment.ERROR);
                    }

                    @Override
                    public void onNext(List<MovieTrailerVM> trailerVMList) {
                        mView.updateTrailers(trailerVMList);
                        mView.updateTrailersViewState(MoviesFragment.DATA_LOADED);
                    }
                }));
    }

    public void cleanUp() {
        mMovieDetailsDataManager.cleanUp();
        mSubscription.unsubscribe();
    }

    public void reset() {
        mSubscription.unsubscribe();
        mSubscription = new CompositeSubscription();
    }

    public void updateFavourite(Movie movie, boolean favourite) {
        mMovieDetailsDataManager.updateFavourite(movie, favourite);
    }
}
