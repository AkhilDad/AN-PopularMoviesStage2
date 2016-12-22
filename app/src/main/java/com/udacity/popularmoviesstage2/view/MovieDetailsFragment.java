package com.udacity.popularmoviesstage2.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.databinding.FragmentMovieDetailsBinding;
import com.udacity.popularmoviesstage2.model.Movie;
import com.udacity.popularmoviesstage2.viewmodel.MovieDetailFragmentVM;
import com.udacity.popularmoviesstage2.viewmodel.MovieDetailVM;

public class MovieDetailsFragment extends Fragment {

    public static final String EXTRA_MOVIE = "EXTRA_MOVIE";
    private Movie mMovie;
    private FragmentMovieDetailsBinding mBinding;

    public MovieDetailsFragment() {
        // Required empty public constructor
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mMovie = getArguments().getParcelable(EXTRA_MOVIE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movie_details, container, false);
        mBinding.setMovieDetailFragmentVM(new MovieDetailFragmentVM(new MovieDetailVM(mMovie)));
        return mBinding.getRoot();
    }


    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
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
