package com.udacity.popularmoviesstage2.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by akhil on 28/12/16.
 */

public class Review {

    @SerializedName("id")
    private String mId;

    @SerializedName("author")
    private String mReviewerName;

    @SerializedName("content")
    private String mReviewComment;

    @SerializedName("url")
    private String mReviewUrl;

    public String getId() {
        return mId;
    }

    public void setId(String id) {
        mId = id;
    }

    public String getReviewerName() {
        return mReviewerName;
    }

    public void setReviewerName(String reviewerName) {
        mReviewerName = reviewerName;
    }

    public String getReviewComment() {
        return mReviewComment;
    }

    public void setReviewComment(String reviewComment) {
        mReviewComment = reviewComment;
    }

    public String getReviewUrl() {
        return mReviewUrl;
    }

    public void setReviewUrl(String reviewUrl) {
        mReviewUrl = reviewUrl;
    }
}
