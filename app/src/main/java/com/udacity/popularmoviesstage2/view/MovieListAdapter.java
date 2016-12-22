package com.udacity.popularmoviesstage2.view;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.udacity.popularmoviesstage2.BR;
import com.udacity.popularmoviesstage2.R;
import com.udacity.popularmoviesstage2.viewmodel.MovieVM;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akhil on 21/06/16.
 */
public class MovieListAdapter extends RecyclerView.Adapter<DataBindingViewHolder>{

    private static final int MOVIE_LIST_ITEM = 0;
    private static final int ERROR_ITEM = 1;
    private List<MovieVM> mMovieList;

    public MovieListAdapter(List<MovieVM> movieList) {
        mMovieList = movieList != null ? movieList : new ArrayList<MovieVM>();
    }

    @Override
    public DataBindingViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new DataBindingViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie_grid, parent, false));
    }

    @Override
    public void onBindViewHolder(DataBindingViewHolder holder, int position) {
        holder.getBinding().setVariable(BR.movieVM, mMovieList.get(position));;
        holder.getBinding().executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return mMovieList.size() > 0 ? mMovieList.size() : 0;
    }

    @Override public int getItemViewType(int position) {
        if (mMovieList != null && mMovieList.size() > 0) {
            return MOVIE_LIST_ITEM;    
        } else {
            return ERROR_ITEM;
        }
        
    }

    public void addMovies(List<MovieVM> movieList) {
        int position = mMovieList.size() - 1;
        mMovieList.addAll(movieList);
        notifyItemRangeChanged(position, getItemCount());
    }

    public void updateMovies(List<MovieVM> movieList) {
        clearAll();
        mMovieList.addAll(movieList);
        notifyDataSetChanged();
    }

    public void clearAll() {
        mMovieList.clear();
        notifyDataSetChanged();
    }
}
