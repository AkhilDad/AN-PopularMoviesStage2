package com.udacity.popularmoviesstage2.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by akhil on 18/06/16.
 */
public class ResponseBean<T> {

    @SerializedName("page")
    private int mPage;

    @SerializedName("results")
    private List<T> mResults;

    @SerializedName("total_results")
    private int mTotalResults;

    @SerializedName("total_pages")
    private int mTotalPages;

    public int getPage() {
        return mPage;
    }

    public void setPage(int page) {
        mPage = page;
    }

    public List<T> getResults() {
        return mResults;
    }

    public void setResults(List<T> results) {
        mResults = results;
    }

    public int getTotalResults() {
        return mTotalResults;
    }

    public void setTotalResults(int totalResults) {
        mTotalResults = totalResults;
    }

    public int getTotalPages() {
        return mTotalPages;
    }

    public void setTotalPages(int totalPages) {
        mTotalPages = totalPages;
    }
}
