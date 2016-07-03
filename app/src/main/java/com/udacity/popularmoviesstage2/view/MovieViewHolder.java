package com.udacity.popularmoviesstage2.view;

import android.databinding.DataBindingUtil;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.udacity.popularmoviesstage2.databinding.ItemMovieGridBinding;

/**
 * Created by akhil on 01/07/16.
 */
public class MovieViewHolder extends RecyclerView.ViewHolder {
    private ItemMovieGridBinding mBinding;
    public MovieViewHolder(View itemView) {
        super(itemView);
        mBinding = DataBindingUtil.bind(itemView);
    }

    public ItemMovieGridBinding getBinding() {
        return mBinding;
    }
}
