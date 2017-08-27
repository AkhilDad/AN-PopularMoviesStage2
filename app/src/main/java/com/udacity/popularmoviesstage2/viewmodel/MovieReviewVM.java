package com.udacity.popularmoviesstage2.viewmodel;

import android.view.View;

import com.udacity.popularmoviesstage2.model.Review;

/**
 * Created by akhil on 01/01/17.
 */
public class MovieReviewVM {

    private Review mReview;
    private ReviewClickHandler mReviewClickHandler;

    public MovieReviewVM(Review review, ReviewClickHandler reviewClickHandler) {
        mReview = review;
        mReviewClickHandler = reviewClickHandler;
    }

    public String getReviewComment() {
        return mReview.getReviewComment() != null ? mReview.getReviewComment() : "";
    }

    public String getReviewerName() {
        return mReview.getReviewerName() != null ? mReview.getReviewerName() : "";
    }

    public void onViewReview(View view) {
        mReviewClickHandler.onReviewClicked(mReview);
    }
}
