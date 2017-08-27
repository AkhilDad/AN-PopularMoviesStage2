package com.udacity.popularmoviesstage2.util;

import android.databinding.BindingAdapter;
import android.view.View;

/**
 * Created by akhil on 14/01/17.
 */

public class BinidingExtensions {

    @BindingAdapter("bind:selected")
    public static void setSelected(View view, boolean selected) {
        view.setSelected(selected);
    }
}
