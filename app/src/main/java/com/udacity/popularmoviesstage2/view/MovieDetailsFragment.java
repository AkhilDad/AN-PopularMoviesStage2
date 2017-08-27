package com.udacity.popularmoviesstage2.view;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popularmoviesstage2.BR;
import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.data.MovieDetailsDataManager;
import com.udacity.popularmoviesstage2.databinding.FragmentMovieDetailsBinding;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.model.Review;
import com.udacity.popularmoviesstage2.model.Trailer;
import com.udacity.popularmoviesstage2.presenter.MovieDetailsPresenter;
import com.udacity.popularmoviesstage2.viewmodel.ErrorClickHandler;
import com.udacity.popularmoviesstage2.viewmodel.FavouriteClickHandler;
import com.udacity.popularmoviesstage2.viewmodel.MovieDetailFragmentVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieDetailVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieReviewVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieTrailerVM;

import java.util.List;

public class MovieDetailsFragment extends Fragment implements MoviesDetailsView {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private Movie mMovie;
    private FragmentMovieDetailsBinding mBinding;
    private MovieDetailsPresenter mMovieDetailsPresenter;
    private MovieDetailFragmentVM mMovieDetailFragmentVM;
    private RecyclerBindingAdapter<MovieTrailerVM> mTrailerAdapter;
    private RecyclerBindingAdapter<MovieReviewVM> mReviewAdapter;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(EXTRA_MOVIE);
        }
        mMovieDetailFragmentVM = new MovieDetailFragmentVM(new MovieDetailVM(mMovie, new FavouriteClickHandler() {
            @Override
            public void onFavourite(Movie movie, boolean favourite) {
                mMovieDetailsPresenter.updateFavourite(movie, favourite);
            }
        }), new ErrorClickHandler() {
            @Override
            public void onRetry(View view) {
                mMovieDetailsPresenter.loadTrailers(mMovie.getId());
            }
        }, new ErrorClickHandler() {
            @Override
            public void onRetry(View view) {
                mMovieDetailsPresenter.loadReviews(mMovie.getId());
            }
        });
        mMovieDetailsPresenter = new MovieDetailsPresenter(this, new MovieDetailsDataManager(getActivity().getApplicationContext()));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        mBinding.setMovieDetailFragmentVM(mMovieDetailFragmentVM);
        mMovieDetailsPresenter.loadReviews(mMovie.getId());
        mMovieDetailsPresenter.loadTrailers(mMovie.getId());
        mTrailerAdapter = new RecyclerBindingAdapter(null, R.layout.item_trailer, BR.trailerVM);
        mReviewAdapter = new RecyclerBindingAdapter(null, R.layout.item_review, BR.reviewVM);
        mBinding.rvMovieTrailers.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.rvMovieReviews.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        mBinding.rvMovieReviews.setAdapter(mReviewAdapter);
        mBinding.rvMovieTrailers.setAdapter(mTrailerAdapter);
        return mBinding.getRoot();
    }

    @Override
    public void updateTrailers(List<MovieTrailerVM> movieTrailerVMList) {
        mTrailerAdapter.updateData(movieTrailerVMList);
        mMovieDetailFragmentVM.setMovieTrailerVMList(movieTrailerVMList);
    }

    @Override
    public void updateReviews(List<MovieReviewVM> movieReviewVMList) {
        mReviewAdapter.updateData(movieReviewVMList);
        mMovieDetailFragmentVM.setMovieReviewVMList(movieReviewVMList);
    }

    @Override
    public void showError(String message) {
        Snackbar.make(getView(), message, Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void updateReviewsViewState(int viewState) {
        switch (viewState) {
            case MoviesFragment.LOADING:
                mMovieDetailFragmentVM.setMovieReviewsRVVisibility(View.GONE);
                mMovieDetailFragmentVM.setMovieReviewsProgressVisibility(View.VISIBLE);
                mMovieDetailFragmentVM.getMovieReviewsErrorVM().setErrorViewVisibility(View.GONE);
                break;
            case MoviesFragment.DATA_LOADED:
                mMovieDetailFragmentVM.setMovieReviewsRVVisibility(View.VISIBLE);
                mMovieDetailFragmentVM.setMovieReviewsProgressVisibility(View.GONE);
                mMovieDetailFragmentVM.getMovieReviewsErrorVM().setErrorViewVisibility(View.GONE);
                break;
            case MoviesFragment.ERROR:
                mMovieDetailFragmentVM.setMovieReviewsRVVisibility(View.GONE);
                mMovieDetailFragmentVM.setMovieReviewsProgressVisibility(View.GONE);
                mMovieDetailFragmentVM.getMovieReviewsErrorVM().setErrorViewVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void updateTrailersViewState(int viewState) {
        switch (viewState) {
            case MoviesFragment.LOADING:
                mMovieDetailFragmentVM.setMovieTrailersRVVisibility(View.GONE);
                mMovieDetailFragmentVM.setMovieTrailersProgressVisibility(View.VISIBLE);
                mMovieDetailFragmentVM.getMovieTrailersErrorVM().setErrorViewVisibility(View.GONE);
                break;
            case MoviesFragment.DATA_LOADED:
                mMovieDetailFragmentVM.setMovieTrailersRVVisibility(View.VISIBLE);
                mMovieDetailFragmentVM.setMovieTrailersProgressVisibility(View.GONE);
                mMovieDetailFragmentVM.getMovieTrailersErrorVM().setErrorViewVisibility(View.GONE);
                break;
            case MoviesFragment.ERROR:
                mMovieDetailFragmentVM.setMovieTrailersRVVisibility(View.GONE);
                mMovieDetailFragmentVM.setMovieTrailersProgressVisibility(View.GONE);
                mMovieDetailFragmentVM.getMovieTrailersErrorVM().setErrorViewVisibility(View.VISIBLE);
                break;
        }
    }

    @Override
    public void clearData() {

    }

    @Override
    public void openTrailerLink(Trailer trailer) {
        Intent appIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("vnd.youtube:" + trailer.getKey()));
        try {
            startActivity(appIntent);
        } catch (ActivityNotFoundException ex) {
            Intent webIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v=" + trailer.getKey()));
            startActivity(webIntent);
        }
    }

    @Override
    public void openReviewLink(Review review) {
        if (review.getReviewUrl() != null) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(review.getReviewUrl()));
            startActivity(intent);
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment
     *
     * @return A new instance of fragment MovieDetailsFragment.
     */
    public static MovieDetailsFragment newInstance(Movie movie) {
        MovieDetailsFragment fragment = new MovieDetailsFragment();
        Bundle args = new Bundle();
        args.putParcelable(EXTRA_MOVIE, movie);
        fragment.setArguments(args);
        return fragment;
    }
}
