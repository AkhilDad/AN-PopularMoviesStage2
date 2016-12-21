package com.udacity.popularmoviesstage2.presenter;

import android.support.annotation.Nullable;
import android.support.annotation.StringDef;


/**
 * Created by akhil on 21/12/16.
 */

public class SortOrder {
    public static final String POPULARITY = "popularity.desc";
    public static final String RATING = "vote_average.desc";
    public static final String FAVORITES = "vote_average.desc";

    @StringDef({POPULARITY, RATING })
    public  @interface SortOrderDef {}

    @SortOrderDef
    @Nullable
    public static String getSortOrder(String sortOrder) {
        if (sortOrder == null) {
            return null;
        }
        switch (sortOrder) {
            case POPULARITY:
                return POPULARITY;
            case RATING:
                return RATING;
            default:
                return null;
        }
    }
}
