package com.udacity.popularmoviesstage2.viewmodel;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmoviesstage2.R;

/**
 * Created by akhil on 23/12/16.
 */

public class BindingAdapters {

    @BindingAdapter({"bind:imageUrl"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.progress_animation)
                .error(R.drawable.no_image)
                .into(view);
    }
}
