package com.udacity.popularmoviesstage2.view;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.data.MoviesDataManager;
import com.udacity.popularmoviesstage2.databinding.FragmentMoviesBinding;
import com.udacity.popularmoviesstage2.presenter.MoviesPresenter;
import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import java.util.List;


public class MoviesFragment extends Fragment implements MoviesView{

    private MoviesPresenter mMoviesPresenter;
    private Context mContext;
    private MovieListAdapter mMovieListAdapter;
    private FragmentMoviesBinding mBinding;
    private RecyclerView mMoviesRV;

    public MoviesFragment() {
        // Required empty public constructor
    }


    public static MoviesFragment newInstance() {
        MoviesFragment fragment = new MoviesFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        mContext = context;
        super.onAttach(context);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {

        }
        mMoviesPresenter = new MoviesPresenter(this, new MoviesDataManager(mContext.getResources()));
        mMoviesPresenter.loadPopularMovies();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_movies, container, false);
        View view = mBinding.getRoot();
        mMoviesRV = (RecyclerView) view.findViewById(R.id.rv_movie_list);
        mMoviesRV.setLayoutManager(new GridLayoutManager(mContext, 2));
        return view;
    }

    @Override
    public void updateMovies(List<MovieVM> movieVMList) {
        mMovieListAdapter = new MovieListAdapter(movieVMList);
        mMoviesRV.setAdapter(mMovieListAdapter);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mMoviesPresenter.cleanUp();
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mContext = null;
    }
}
