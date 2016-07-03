package com.udacity.popularmoviesstage2.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.popularmoviesstage2.BR;
import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import java.util.List;

/**
 * Created by akhil on 21/06/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<MovieViewHolder>{

    private List<MovieVM> mMovieList;

    public MovieListAdapter(List<MovieVM> movieList) {
        mMovieList = movieList;
    }

    @Override
    public MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MovieViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(MovieViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.movieVM, mMovieList.get(position));
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovieList != null ? mMovieList.size() : 0;
    }
}
